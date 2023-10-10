package com.nomura.sandeep.chronicle.dp;


public class EditDistance {

    private int editDistance(String source, int sourcePosition, String target, int targetPosition) {
        System.out.printf("source = %s, sourcePositon = %d , target = %s , targetPosition = %d \n", source, sourcePosition, target, targetPosition);
/*
        if (source.equals(target)) {
            return 0;
        }
*/

        if ((target.length() - 1) == sourcePosition) {
            return source.length() - 1 - targetPosition;
        }
        if ((source.length() - 1) == targetPosition) {
            return target.length() - sourcePosition - 1;
        }

        //if all characters have to deleted.
        if (source.length() == 0) {
            return target.length();
        }
        //if all characters have to be inserted
        if (target.length() == 0) {
            return source.length();
        }

        if (source.charAt(sourcePosition) == target.charAt(targetPosition)) {
            return editDistance(source, sourcePosition + 1, target, targetPosition + 1);
        } else {
/*
        int added = editDistance(add(source, target, targetPosition), sourcePosition + 1, target, targetPosition + 1);
        int edited = editDistance(replace(source, target, targetPosition), sourcePosition + 1, target, targetPosition + 1);
        int deleted = editDistance(delete(source, sourcePosition), sourcePosition, target, targetPosition + 1);*/

            int deleted = editDistance(source, sourcePosition, target, targetPosition + 1);
            int edited = editDistance(source, sourcePosition + 1, target, targetPosition + 1);
            int added = editDistance(source, sourcePosition + 1, target, targetPosition);

            return Math.min(Math.min(added, edited), deleted) + 1;
        }
    }


    private String add(String source, String target, int position) {
        char[] newString = new char[source.length() + 1];
        for (int i = 0; i <= source.length(); i++) {
            if (i < position) {
                newString[i] = source.charAt(i);
            } else if (i == position) {
                newString[i] = target.charAt(position);
            } else {
                newString[i] = source.charAt(i - 1);
            }
        }
        return new String(newString);
    }

    private String replace(String source, String target, int position) {
        char newString[] = source.toCharArray();
        newString[position] = target.charAt(position);
        return new String(newString);
    }

    private String delete(String source, int position) {
        char[] newString = new char[source.length() - 1];
        for (int i = 0; i < source.length() - 1; i++) {
            if (i < position) {
                newString[i] = source.charAt(i);
            } else {
                newString[i] = source.charAt(i + 1);
            }
        }
        return new String(newString);
    }

    public static void main(String[] args) {
        EditDistance e = new EditDistance();

        System.out.printf(" %s \n", e.add("SUNDAY", "SATURDAY", 1));
        System.out.printf(" %s \n", e.replace("SUNDAY", "SATURDAY", 1));
        System.out.printf(" %s \n", e.delete("SUNDAY", 1));
        System.out.printf(" %s \n", e.editDistance("SUNDAY", 0, "SATURDAY", 0));
    }
}

;