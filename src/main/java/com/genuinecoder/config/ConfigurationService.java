package com.genuinecoder.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class ConfigurationService {
    private final File SETTTINGS_FILE = new File("settings.json");
    private Gson gson = new GsonBuilder().create();

    public ConfigModel getConfiguration() throws Exception {
        if (!SETTTINGS_FILE.exists()) {
            createSettingsFile();
        }
        return getConfigurationFromFile();
    }

    private ConfigModel getConfigurationFromFile() throws IOException {
        ConfigModel configModel = new ConfigModel();
        try (Reader reader = new FileReader(SETTTINGS_FILE)) {
            return gson.fromJson(reader, ConfigModel.class);
        }
    }

    private void createSettingsFile() throws IOException {
        ConfigModel configModel = new ConfigModel();
        try (Writer writer = new FileWriter(SETTTINGS_FILE, false)) {
            gson.toJson(configModel, writer);
        }
    }
}
