package com.ken.xloghelper;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import android.os.Process;

import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

public class PPLog {
    private static boolean release = true;
    public static String tag = "PP";

    public PPLog() {
    }

    public static void load() {
        System.loadLibrary("c++_shared");
        System.loadLibrary("marsxlog");
    }

    public static void init(Context context, boolean debug, String logPath) {
        try {
            load();
            String processName = null;
            ActivityManager am = (ActivityManager) context.getSystemService(android.content.Context.ACTIVITY_SERVICE);
            if (am == null) {
                return;
            }

            int myPid = Process.myPid();
            Iterator var3 = am.getRunningAppProcesses().iterator();

            while (var3.hasNext()) {
                ActivityManager.RunningAppProcessInfo appProcessInfo = (ActivityManager.RunningAppProcessInfo) var3.next();
                if (appProcessInfo.pid == myPid) {
                    processName = appProcessInfo.processName;
                    break;
                }
            }

            if (TextUtils.isEmpty(processName)) {
                return;
            }

            String logFileName = !processName.contains(":") ? "uplog" : "uplog_" + processName.substring(processName.indexOf(":") + 1);
            String cachePath = context.getFilesDir() + "/pplog";
            if (debug) {
                Xlog.appenderOpen(0, 0, "", logPath, logFileName, 0, "d3648cdc0c57c21e725e08e40afdf58a1c7b940d4c2323f9889c68e913d674b0b32eae0f5db985a40025d2101f7a4c465471ebb531e932d18c753ad023e6803e");
                Xlog.setConsoleLogOpen(true);
            } else {
                Xlog.appenderOpen(1, 0, cachePath, logPath, logFileName, 0, "d3648cdc0c57c21e725e08e40afdf58a1c7b940d4c2323f9889c68e913d674b0b32eae0f5db985a40025d2101f7a4c465471ebb531e932d18c753ad023e6803e");
                Xlog.setConsoleLogOpen(false);
            }
            Log.setLogImp(new Xlog());
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public static void close() {
        Log.appenderClose();
    }

    public static boolean isRelease() {
        return release;
    }

    public static void setRelease(boolean release) {
        PPLog.release = release;
    }

    public static void logRelease(String tag, String info) {
        if (release) {
            d(tag, info);
        }

    }

    public static void d(Exception e) {
        if (e != null) {
            Log.w(tag, getStringForException(e));
        }

    }

    public static void d(String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.d(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void d(String tag, String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.d(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void i(String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.i(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void i(String tag, String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.i(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void v(String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.v(tag, names[names.length - 1], new Object[]{s.getMethodName(), s.getLineNumber(), info, (Object[]) null});
            }
        }

    }

    public static void v(String tag, String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.v(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void e(String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.e(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void e(String tag, String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.e(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void w(String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.w(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void w(String tag, String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 1) {
                StackTraceElement s = stack[1];
                String[] names = s.getClassName().split("\\.");
                Log.w(tag, names[names.length - 1], s.getMethodName(), s.getLineNumber(), info);
            }
        }

    }

    public static void detail(String info) {
        detail(tag, info);
    }

    public static void detail(String tag, String info) {
        if (info != null) {
            StackTraceElement[] stack = (new Throwable()).getStackTrace();
            if (stack.length > 3) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(info);
                buffer.append("<--");
                String[] names = stack[1].getClassName().split("\\.");
                buffer.append(names[names.length - 1] + "第" + stack[1].getLineNumber() + "行<--");
                names = stack[2].getClassName().split("\\.");
                buffer.append(names[names.length - 1] + "第" + stack[2].getLineNumber() + "行<--");
                names = stack[3].getClassName().split("\\.");
                buffer.append(names[names.length - 1] + "第" + stack[3].getLineNumber() + "行");
                Log.d(tag, buffer.toString());
            }
        }

    }

    public static String getStringForException(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            try {
                if (sw != null) {
                    sw.close();
                }

                if (pw != null) {
                    pw.close();
                }
            } catch (IOException var11) {
                var11.printStackTrace();
            }

        }

        return TextUtils.isEmpty(sw.toString()) ? e.getMessage() : "\r\n" + sw.toString() + "\r\n";
    }
}
