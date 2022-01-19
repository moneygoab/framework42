package org.framework42.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;

public class RedDaysInSweden {
    

    public static boolean IsRedDay(LocalDateTime time){
        
        boolean isRed = false;

        switch(time.getMonth()){
            case JANUARY:
                if(time.getDayOfMonth() == 1){
                    isRed = true;
                }else if(time.getDayOfMonth() == 6){
                    isRed = true;
                }
            break;

            case MAY:
                if(time.getDayOfMonth() == 1){
                    isRed = true;
                }
            break;


            case JUNE:
                if(time.getDayOfMonth() == 6){
                    isRed = true;
                }
            break;


            case DECEMBER:
                if(time.getDayOfMonth() == 24){
                    isRed = true;
                }else if(time.getDayOfMonth() == 25){
                    isRed = true;
                }else if(time.getDayOfMonth() == 26){
                    isRed = true;
                }else if(time.getDayOfMonth() == 31){
                    isRed = true;
                }
            break;
            
        }



        LocalDateTime easterSunday = findEasterSundayInYear(time.getYear());

        if(easterSunday.minusDays(2).getMonth() == time.getMonth() && easterSunday.minusDays(2).getDayOfMonth() == time.getDayOfMonth()){
            isRed = true;
        }

        if(easterSunday.minusDays(1).getMonth() == time.getMonth() && easterSunday.minusDays(1).getDayOfMonth() == time.getDayOfMonth()){
            isRed = true;
        }


        if(easterSunday.getMonth() == time.getMonth() && easterSunday.getDayOfMonth() == time.getDayOfMonth()){
            isRed = true;
        }

        if(easterSunday.plusDays(1).getMonth() == time.getMonth() && easterSunday.plusDays(1).getDayOfMonth() == time.getDayOfMonth()){
            isRed = true;
        }

        if(easterSunday.plusDays(39).getMonth() == time.getMonth() && easterSunday.plusDays(39).getDayOfMonth() == time.getDayOfMonth()){
            isRed = true;
        }

        if(easterSunday.plusDays(49).getMonth() == time.getMonth() && easterSunday.plusDays(49).getDayOfMonth() == time.getDayOfMonth()){
            isRed = true;
        }
    



        LocalDateTime findMidsummer = LocalDateTime.of(time.getYear(), Month.JUNE, 20, 0 ,0);

        while (findMidsummer.getDayOfMonth()<=26) {

            if(findMidsummer.getDayOfWeek()== DayOfWeek.SATURDAY) {


                if(Month.JUNE == time.getMonth() && findMidsummer.getDayOfMonth() == time.getDayOfMonth()){
                    isRed = true;
                }

                if(Month.JUNE == time.getMonth() && findMidsummer.getDayOfMonth()-1 == time.getDayOfMonth()){
                    isRed = true;
                }
                break;

            } else {

                findMidsummer = findMidsummer.plusDays(1);
            }

        }


        LocalDateTime allahelgonDate = LocalDateTime.of(time.getYear(), Month.OCTOBER, 31, 0, 0);
        while(allahelgonDate.getDayOfWeek()!=DayOfWeek.SATURDAY) {
            allahelgonDate = allahelgonDate.plusDays(1);
        }


        if(allahelgonDate.getMonth() == time.getMonth() && allahelgonDate.getDayOfMonth() == time.getDayOfMonth()){
            isRed = true;
        }


        return isRed;
    }




    private static LocalDateTime findEasterSundayInYear(int year) {

        int a = year % 19,
                b = year / 100,
                c = year % 100,
                d = b / 4,
                e = b % 4,
                g = (8 * b + 13) / 25,
                h = (19 * a + b - d - g + 15) % 30,
                j = c / 4,
                k = c % 4,
                m = (a + 11 * h) / 319,
                r = (2 * e + 2 * j - k - h + m + 32) % 7,
                n = (h - m + r + 90) / 25,
                p = (h - m + r + n + 19) % 32;

        return LocalDateTime.of(year, n, p, 0, 0);

    }


    public static void main(String[] args) {
        

        System.out.print(IsRedDay(LocalDateTime.now()));

    }
}
