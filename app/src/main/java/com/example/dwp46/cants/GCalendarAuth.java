package com.example.dwp46.cants;

import android.content.Context;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GCalendarAuth {
    private static final String APPLICATION_NAME = "Ementas Calendar";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    private static Credential getCredentials(Context is) throws IOException
    {
        return GoogleCredential.fromStream((is.getAssets().open("EmentasUM-5029bdb036ca.json"))).createScoped(SCOPES);
    }

    public static Calendar getService(Context is) throws IOException
    {
        final NetHttpTransport HTTP_TRANSPORT = (NetHttpTransport) AndroidHttp.newCompatibleTransport();
        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(is))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}