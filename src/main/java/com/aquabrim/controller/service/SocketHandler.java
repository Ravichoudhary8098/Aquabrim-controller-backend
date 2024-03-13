package com.aquabrim.controller.service;

import com.aquabrim.controller.constants.Constants;
import com.aquabrim.controller.entity.Controller;
import com.aquabrim.controller.entity.Tank;
import com.aquabrim.controller.entity.Timer;
import com.aquabrim.controller.entity.UserActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.aquabrim.controller.constants.Constants.USER_ACTIVITY_RECORDS_TO_SAVE;

@Service
@Slf4j
public class SocketHandler {

    private Map<String, Socket> device_id_to_connection;
    private Map<String, Socket> device_id_to_connection_tx;
    private final Object mutex = new Object();

    private static final String headerString = "10101011";
    private static final int commandLength = 7;

    @Autowired
    ControllerService controllerService;
    @Autowired
    UserActivityService userActivityService;
    @Autowired
    ConvertAndSaveService convertAndSave;

    public void handleRequest(Socket conn) {
        int receivedBytes = 0;
        StringBuilder receivedCommand = new StringBuilder();
        boolean firstCommand = true;
        boolean connFromLocalServer = true;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            PrintWriter out = new PrintWriter(conn.getOutputStream(), true);

            while (true) {
                char[] buf = new char[1];
                conn.setSoTimeout(2000);
                try {
                    if (in.read(buf, 0, 1) == -1) {
                        closeConnectionCleanMemory(conn);
                        return;
                    }
                } catch (SocketTimeoutException e) {
                    if (receivedBytes > 0) {
                        closeConnectionCleanMemory(conn);
                        return;
                    }
                    receivedBytes = 0;
                    receivedCommand.setLength(0);
                    continue;
                }

                int intValue = (int) buf[0];
                if (intValue == 42 && connFromLocalServer) {
                    char[] buffer = new char[1024];
                    if (in.read(buffer, 0, 1024) == -1) {
                        // Error reading
                    }

                    String string_repr = new String(buffer).trim();
                    String[] components = string_repr.split(" ");
                    String action_to_do = components[0];
                    String device_id_to_do_it_on = components[1].substring(0, 24);

                    synchronized (mutex) {
                        boolean device_connected = device_id_to_connection.containsKey(device_id_to_do_it_on);
                        boolean device_connected_tx = device_id_to_connection_tx.containsKey(device_id_to_do_it_on);

                        if ("is_connected".equals(action_to_do)) {
                            if (device_connected) {
                                send_data_to_device(device_id_to_do_it_on, "a?", null, conn);
                                out.println("yes");
                            } else {
                                out.println("no");
                            }
                        } else if ("is_connected_tx".equals(action_to_do)) {
                            if (device_connected_tx) {
                                out.println("yes");
                            } else {
                                out.println("no");
                            }
                        } else if ("send".equals(action_to_do)) {
                            if (device_connected) {
                                String command_to_send = components[1];
                                byte[] byte_array = convert_command_to_send_into_byte_array(command_to_send, conn);
                                if (byte_array == null) {
                                    return;
                                }
                                send_data_to_device(device_id_to_do_it_on, "", byte_array, conn);
                            }
                        }
                    }

                    conn.close();
                    return;
                } else {
                    connFromLocalServer = false;
                    String binaryRepresentation = Integer.toBinaryString(intValue);
                    if (binaryRepresentation.length() != 8) {
                        int paddingLength = 8 - binaryRepresentation.length();
                        StringBuilder paddingString = new StringBuilder();
                        for (int i = 0; i < paddingLength; i++) {
                            paddingString.append("0");
                        }
                        binaryRepresentation = paddingString.toString() + binaryRepresentation;
                    }
                    receivedBytes++;
                    receivedCommand.append(binaryRepresentation);
                }

                if (receivedBytes == commandLength) {
                    receivedBytes = 0;

                    if (!receivedCommand.substring(0, 8).equals(headerString)) {
                        closeConnectionCleanMemory(conn);
                        return;
                    } else {
                        receivedCommand.delete(0, 8);
                    }

                    if (firstCommand) {
                        firstCommand = false;
                    }

                    if (receivedCommand.charAt(0) == '1') {
                        String received_command = "0" + receivedCommand.substring(1);
                        synchronized (mutex) {
                            device_id_to_connection_tx.put(received_command.substring(0, 24), conn);
                        }
                    } else {
                        synchronized (mutex) {
                            device_id_to_connection.put(receivedCommand.substring(0, 24), conn);
                        }
                        collectDataFromDeviceUsingTCP(String.valueOf(receivedCommand));
                    }

                    receivedCommand.setLength(0);
                }
            }
        } catch (IOException e) {
            closeConnectionCleanMemory(conn);
        }
    }

    private void send_data_to_device(String device_id, String data, byte[] direct_byte_array, Socket conn) {
        synchronized (mutex) {
            Socket connObject = device_id_to_connection.get(device_id);
            byte[] data_to_send;
            if (direct_byte_array != null) {
                data_to_send = direct_byte_array;
            } else {
                data_to_send = data.getBytes();
            }
            if (data_to_send.length == 2) {
                try {
                    connObject.getOutputStream().write(data_to_send);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void closeConnectionCleanMemory(Socket conn) {
        try {
            conn.close();
            synchronized (mutex) {
                device_id_to_connection.entrySet().removeIf(entry -> entry.getValue().equals(conn));
                device_id_to_connection_tx.entrySet().removeIf(entry -> entry.getValue().equals(conn));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] convert_command_to_send_into_byte_array(String command_to_send, Socket conn) {
        byte[] byte_array = new byte[6];
        for (int i = 0; i < 6; i++) {
            String byt = command_to_send.substring(i * 8, (i + 1) * 8);
            int byteVal = Integer.parseInt(byt, 2);
            byte_array[i] = (byte) byteVal;
        }
        return byte_array;
    }

    private void collectDataFromDeviceUsingTCP(String inputData) {
        System.out.println("In function collect data:");
        System.out.println(inputData);

        List<String> array = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            array.add(inputData.substring(i * 8, (i + 1) * 8));
        }

        int idnoInt = Integer.parseInt(array.get(0) + array.get(1) + array.get(2), 2);
        String idno = String.format("%06x", idnoInt);
        System.out.println(idno);

        if (!searchDeviceId(idno)) {
            return;
        }

        Controller controller = controllerService.findByDeviceId(idno);
        String commandHex = convertBinaryStringToHexString(inputData);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(5) + TimeUnit.MINUTES.toMillis(30));

        UserActivity userActivity = new UserActivity();
        userActivity.setController(controller);
        userActivity.setUser(controller.getUser());
        userActivity.setCommandSent(commandHex);
        userActivity.setSentOrReceived("received");
        userActivity.setTimestamp(timestamp.toLocalDateTime());
        userActivityService.save(userActivity);

        System.out.println("Preparing to parse: " + new String(inputData.getBytes(), StandardCharsets.UTF_8).trim());
        convertAndSave.convertAndSave(idno, inputData);

        // Delete excess user activity records
        List<Long> activityIds = userActivityService.getLatestActivityIds(controller.getUser());
        if (activityIds.size() > USER_ACTIVITY_RECORDS_TO_SAVE) {
            userActivityService.deleteRecords(activityIds.subList(USER_ACTIVITY_RECORDS_TO_SAVE, activityIds.size()));
        }
    }

    public boolean searchDeviceId(String inDeviceId) {
        List<String> allDeviceIds = controllerService.getAllDeviceIds();
        return allDeviceIds.contains(inDeviceId);
    }

    public static String convertBinaryStringToHexString(String stringToSend) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stringToSend.length(); i += 4) {
            String part = stringToSend.substring(i, Math.min(i + 4, stringToSend.length()));
            int decimalValue = Integer.parseInt(part, 2);
            String hexValue = Integer.toHexString(decimalValue);
            result.append(hexValue.charAt(hexValue.length() - 1));
        }
        return result.toString();
    }
}
