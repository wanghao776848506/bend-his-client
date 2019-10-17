package com.bend.common.framework.util;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import com.sun.jna.win32.StdCallLibrary;

/** Simple example of JNA interface mapping and usage. */
public class HelloWorld {

    // This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public interface CLibrary extends Library {
//        CLibrary INSTANCE = (CLibrary)
//            Native.load((Platform.isWindows() ? "msvcrt" : "c"),
//                                CLibrary.class);
//
//        void printf(String format, Object... args);
        CLibrary INSTANCE = Native.load("dll/yyjk",CLibrary.class);
        int DisConnectAppServer_cxjb();
        int ConnectAppServer_cxjb(String aLoginID,String aUserPwd);
        int CallService_cxjb(String aFuncCode); //aFuncCode：交易代码，各业务调用所对应的交易代码参见：接口明细说明
        int ReadCardInfo_cxjb(String aReaderPort,String aCardPasswd);
    }

    public static void main(String[] args) {
//        CLibrary.INSTANCE.printf("Hello, World\n");
//        for (int i=0;i < args.length;i++) {
//            CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
//        }
//        String path = HelloWorld.class.getResource("/").getPath()+"/";
//        System.load(path+"/yyjk.dll");
//        NativeLibrary.addSearchPath("yyjk.dll", path);
        int res = CLibrary.INSTANCE.ConnectAppServer_cxjb("cpq2677","cbwsy16");
        System.out.println(res);
    }
}