package com.elimei.elimei.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SaveBitamptoBmp {
    public File Compress(Bitmap bitmap, String name) {

        int w = bitmap.getWidth(), h = bitmap.getHeight();

        int[] pixels = new int[w * h];

        bitmap.getPixels(pixels, 0, w, 0, 0, w, h);

        byte[] rgb = addBMP_RGB_888(pixels, w, h);

        byte[] header = addBMPImageHeader(rgb.length);

        byte[] infos = addBMPImageInfosHeader(w, h);

        byte[] buffer = new byte[54 + rgb.length];

        System.arraycopy(header, 0, buffer, 0, header.length);

        System.arraycopy(infos, 0, buffer, 14, infos.length);

        System.arraycopy(rgb, 0, buffer, 54, rgb.length);

        File father = new File(Environment.getExternalStorageDirectory().getPath(), "carecord/");
        if (!father.exists() && !father.isDirectory()) {
            try {
                father.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File file = new File(father, name + ".bmp");


        if (!file.exists() && !file.isDirectory()) {
            try {

                file.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        try {
            /*  44 */
            FileOutputStream fos = new FileOutputStream(file);
            /*  45 */
            fos.write(buffer);
            /*  46 */
        } catch (FileNotFoundException e) {
            /*  47 */
            e.printStackTrace();
            /*  48 */
        } catch (IOException e) {
            /*  49 */
            e.printStackTrace();
        }
        /*  51 */
        return file;
    }


    private byte[] addBMPImageHeader(int size) {
        /*  56 */
        byte[] buffer = new byte[14];
        /*  57 */
        buffer[0] = 66;
        /*  58 */
        buffer[1] = 77;
        /*  59 */
        buffer[2] = (byte) (size >> 0);
        /*  60 */
        buffer[3] = (byte) (size >> 8);
        /*  61 */
        buffer[4] = (byte) (size >> 16);
        /*  62 */
        buffer[5] = (byte) (size >> 24);
        /*  63 */
        buffer[6] = 0;
        /*  64 */
        buffer[7] = 0;
        /*  65 */
        buffer[8] = 0;
        /*  66 */
        buffer[9] = 0;
        /*  67 */
        buffer[10] = 54;
        /*  68 */
        buffer[11] = 0;
        /*  69 */
        buffer[12] = 0;
        /*  70 */
        buffer[13] = 0;
        /*  71 */
        return buffer;
    }

    private byte[] addBMPImageInfosHeader(int w, int h) {
        /*  75 */
        byte[] buffer = new byte[40];
        /*  76 */
        buffer[0] = 40;
        /*  77 */
        buffer[1] = 0;
        /*  78 */
        buffer[2] = 0;
        /*  79 */
        buffer[3] = 0;
        /*  80 */
        buffer[4] = (byte) (w >> 0);
        /*  81 */
        buffer[5] = (byte) (w >> 8);
        /*  82 */
        buffer[6] = (byte) (w >> 16);
        /*  83 */
        buffer[7] = (byte) (w >> 24);
        /*  84 */
        buffer[8] = (byte) (h >> 0);
        /*  85 */
        buffer[9] = (byte) (h >> 8);
        /*  86 */
        buffer[10] = (byte) (h >> 16);
        /*  87 */
        buffer[11] = (byte) (h >> 24);
        /*  88 */
        buffer[12] = 1;
        /*  89 */
        buffer[13] = 0;
        /*  90 */
        buffer[14] = 24;
        /*  91 */
        buffer[15] = 0;
        /*  92 */
        buffer[16] = 0;
        /*  93 */
        buffer[17] = 0;
        /*  94 */
        buffer[18] = 0;
        /*  95 */
        buffer[19] = 0;
        /*  96 */
        buffer[20] = 0;
        /*  97 */
        buffer[21] = 0;
        /*  98 */
        buffer[22] = 0;
        /*  99 */
        buffer[23] = 0;
        /* 100 */
        buffer[24] = -32;
        /* 101 */
        buffer[25] = 1;
        /* 102 */
        buffer[26] = 0;
        /* 103 */
        buffer[27] = 0;
        /* 104 */
        buffer[28] = 2;
        /* 105 */
        buffer[29] = 3;
        /* 106 */
        buffer[30] = 0;
        /* 107 */
        buffer[31] = 0;
        /* 108 */
        buffer[32] = 0;
        /* 109 */
        buffer[33] = 0;
        /* 110 */
        buffer[34] = 0;
        /* 111 */
        buffer[35] = 0;
        /* 112 */
        buffer[36] = 0;
        /* 113 */
        buffer[37] = 0;
        /* 114 */
        buffer[38] = 0;
        /* 115 */
        buffer[39] = 0;
        /* 116 */
        return buffer;
    }

    private byte[] addBMP_RGB_888(int[] b, int w, int h) {
        /* 120 */
        int len = b.length;
        /* 121 */
        System.out.println(b.length);
        /* 122 */
        byte[] buffer = new byte[w * h * 3];
        /* 123 */
        int offset = 0;
        int i;
        /* 124 */
        for (i = len - 1; i >= w; i -= w) {

            /* 126 */
            int end = i, start = i - w + 1;
            /* 127 */
            for (int j = start; j <= end; j++) {
                /* 128 */
                buffer[offset] = (byte) (b[j] >> 0);
                /* 129 */
                buffer[offset + 1] = (byte) (b[j] >> 8);
                /* 130 */
                buffer[offset + 2] = (byte) (b[j] >> 16);
                /* 131 */
                offset += 3;
            }
        }
        return buffer;
    }
}
