package project;

public class Helpers {
        static int indexOf(char[] arr, char c) {
            for (int i = 0; i < arr.length; i++)
                if (arr[i] == c)
                    return i;
            return -1;
        }
}
