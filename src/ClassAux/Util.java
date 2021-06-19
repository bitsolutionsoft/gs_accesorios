package ClassAux;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Util {
    public static void Error(String titulo, String mensaje){
        NotificationType notificationType= NotificationType.ERROR;
        TrayNotification trayNotification=new TrayNotification();
        trayNotification.setTitle(titulo);
        trayNotification.setMessage(mensaje);
        trayNotification.setNotificationType(notificationType);
        trayNotification.setAnimationType(AnimationType.FADE);
        trayNotification.showAndDismiss(new Duration(1500));
        trayNotification.showAndWait();
    }


    public static void Advertencia(String titulo, String mensaje){
        NotificationType notificationType= NotificationType.WARNING;
        TrayNotification trayNotification=new TrayNotification();
        trayNotification.setTitle(titulo);
        trayNotification.setMessage(mensaje);
        trayNotification.setNotificationType(notificationType);
        trayNotification.setAnimationType(AnimationType.FADE);
        trayNotification.showAndDismiss(new Duration(1500));
        trayNotification.showAndWait();
    }


    public static void Exito(String titulo, String mensaje){
        NotificationType notificationType= NotificationType.SUCCESS;
        TrayNotification trayNotification=new TrayNotification();
        trayNotification.setTitle(titulo);
        trayNotification.setMessage(mensaje);
        trayNotification.setNotificationType(notificationType);
        trayNotification.setAnimationType(AnimationType.FADE);
        trayNotification.showAndDismiss(new Duration(1500));
        trayNotification.showAndWait();
    }

}
