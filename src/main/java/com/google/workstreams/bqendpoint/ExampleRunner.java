package com.google.workstreams.bqendpoint;

import com.looker.rtl.AuthSession;
import com.looker.rtl.ConfigurationProvider;
import com.looker.rtl.SDKResponse;
import com.looker.rtl.Transport;
import com.looker.sdk.ApiSettings;
import com.looker.sdk.DBConnection;
import com.looker.sdk.LookerSDK;
import com.looker.sdk.User;
import com.looker.sdk.WriteDBConnection;
import com.looker.sdk.WriteQuery;
import com.looker.sdk.Query;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

public class ExampleRunner {

  LookerSDK sdk;

  public static void main(String[] args) {

    try {
      new ExampleRunner()
          .configure()
          // .createConnection();
          // .runCallMe()
          // .createQuery()
          .runQuery();

      System.out.println("done");
    } catch (Error e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

  public ExampleRunner configure() {

    Dotenv dotenv = Dotenv.load();
    dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    // Setup the settings from system properties
    ConfigurationProvider settings = ApiSettings.fromMap(new HashMap<>());
    settings.readConfig();
    AuthSession session = new AuthSession(settings, new Transport(settings));
    sdk = new LookerSDK(session);
    return this;
  }

  // public ExampleRunner runCallMe() {
  // User user = sdk.ok(sdk.me());
  // System.out.println("User name is " + user.getDisplay_name());
  // return this;
  // }

  // public ExampleRunner createConnection() {

  // WriteDBConnection writeDBConnection = new WriteDBConnection(null, null, null,
  // null, null, null, null, null, null,
  // null, null, null, null, null, null, null, null, null, null, null, null, null,
  // null, null, null, null, null,
  // null, null, null, null, null, null, null, null);

  // writeDBConnection.set

  // SDKResponse connection = sdk.create_connection(writeDBConnection);

  // // sdk.create_query(null);

  // return this;

  // }

  public ExampleRunner createQuery() {

    WriteQuery writeQuery = new WriteQuery("joined_daily", "weather_daily", null, null, null, null, null, null, null,
        null, null,
        null, null,
        null, null, null, null, null, null);

    writeQuery.setFields(
        new String[] { "sensor_daily.max_temp_indoor", "sensor_daily.dt_date", "weather_daily.avg_temp_outdoor" });
    writeQuery.setFill_fields(new String[] { "sensor_daily.dt_date" });

    // SDKResponse response = sdk.create_query(writeQuery);

    return this;

  }

  public ExampleRunner runQuery() {

    SDKResponse response = sdk.run_query("141", "json");

    System.out.println(response);

    return this;
  }

}