package com.etaTech;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.IntStream;

/****************************************************
 *** Created by Fady Fouad on 7/7/2019 at 23:04.***
 ***************************************************/

public class Main {
    public static void main(String[] args) {
        int []days  = {0,2,3,4,5,6};
        int fromHour=8;
        int toHour=22;

        String state = getStatusOfDoctorOpenedOrClosed(days,fromHour,toHour);
        System.out.println(state);


    }

// Function should return a string depending on current date and time, as an example:
// 1) “Opened: Closes at 8 PM”
// 2) “Closed: Opens at Mon 4 PM”
// doctorDays: The days of the week the doctor is available (0 = Saturday, 1 = Sunday, .... , 6 = Friday)
// fromHour: for the defined day in the same index, the start hour in 24h format
// toHour: for the defined day in the same index, the end hour in 24h format

    private static String getStatusOfDoctorOpenedOrClosed(int[] doctorDays, int fromHour, int toHour) {
        int []workHours = IntStream.rangeClosed(fromHour,toHour).toArray();
        StringBuilder state = new StringBuilder();

        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int hourNow = c.get(Calendar.HOUR_OF_DAY);
        HashMap<Integer,String>days = new HashMap<>();
        days.put(0,"Saturday");
        days.put(1,"Sunday");
        days.put(2,"Monday");
        days.put(3,"Tuesday");
        days.put(4,"Wednesday");
        days.put(5,"Thursday");
        days.put(6,"Friday");

        boolean containsDay = Arrays.stream(doctorDays).anyMatch(i -> i == dayOfWeek);
        boolean containsHour = Arrays.stream(workHours).anyMatch(i -> i == hourNow);

        int nextWorkDay;
        try {
            nextWorkDay = doctorDays[dayOfWeek]+1;
        }catch (IndexOutOfBoundsException e){
            nextWorkDay = doctorDays[0];
        }

        if (containsDay&&containsHour){
            state.append("Doctor is available Today\n close at").append(toHour);
        }
        if (containsDay&&!containsHour){
            state.append("Doctor is close " + "Open At ").append(days.get(nextWorkDay)).append(" at ").append(fromHour);
        }
        if (!containsDay){
            state.append("Doctor is not available Today\n open at").append(days.get(nextWorkDay)).append(" at ").append(toHour);
        }
        return state.toString();
    }

}
