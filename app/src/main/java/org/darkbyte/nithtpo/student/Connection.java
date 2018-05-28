package org.darkbyte.nithtpo.student;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connection {
    Context context;
    boolean isConnected;
    public Connection(Context context) {
        this.context = context;
    }

    public boolean isInternet() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
//            NetworkInfo[] info = manager.getAllNetworkInfo();
//            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            NetworkInfo mobile = manager .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//            if (wifi.isConnected()){
//                // If Wi-Fi connected
//            }
//
//            if (mobile.isConnected()) {
//                // If Internet connected
//            }


            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
             isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }  return isConnected;
//
//            if (info != null)
//                for (int i = 0; i < info.length; i++)
//                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    } else if (
//                            info[i].getState() == android.net.NetworkInfo.State.DISCONNECTED ||
//                                    info[i].getState() == android.net.NetworkInfo.State.DISCONNECTED) {
//
//                      //  Toast.makeText(context, " No Internet Connection ", Toast.LENGTH_LONG).show();
//                        return false;
//                    }
//        }
//        return true;
        }
    }

