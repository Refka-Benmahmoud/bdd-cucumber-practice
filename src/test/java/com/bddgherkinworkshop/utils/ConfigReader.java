package com.bddgherkinworkshop.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class ConfigReader {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties introuvable dans src/test/resources");
            }
            PROPS.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Impossible de lire config.properties", e);
        }
    }

    private ConfigReader() {
    }

    public static String get(String cle) {
        String valeur = System.getProperty(cle, PROPS.getProperty(cle));
        if (valeur == null) {
            throw new IllegalArgumentException("Clé absente de config.properties : " + cle);
        }
        return valeur.trim();
    }

    public static int getInt(String cle) {
        return Integer.parseInt(get(cle));
    }

    public static boolean getBoolean(String cle) {
        return Boolean.parseBoolean(get(cle));
    }
}
