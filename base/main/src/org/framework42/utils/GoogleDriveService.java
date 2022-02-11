package org.framework42.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.apache.log4j.Logger;
import com.google.api.services.drive.model.File;
import org.framework42.model.MimeType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoogleDriveService {

    private static GoogleDriveService service = null;
    private static final Logger logger = Logger.getLogger("org.framework42.base");

    /**
     * how to use
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        GoogleDriveService service = new GoogleDriveService("/home/fredrik/","/home/fredrik/Downloads/client_secret_752418483976-6lja79ob44msu5kriom816d4i7sg7vk4.apps.googleusercontent.com.json");
        String folderId = service.createFolder("testFolder");

    }


    final String APPLICATION_NAME = "Google drive moneygo";
    final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    final String TOKENS_DIRECTORY_PATH;
    final NetHttpTransport HTTP_TRANSPORT;
    final String CREDENTIALS_FILE_PATH;
    final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE, DriveScopes.DRIVE_APPDATA, DriveScopes.DRIVE_FILE, DriveScopes.DRIVE_METADATA);

    private Credential credential;

    private Drive driveService;


    private void getCredentials() throws IOException {
        // Load client secrets.

        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }


    public GoogleDriveService(String tokenPath, String certFile) throws IOException, GeneralSecurityException {
        this.TOKENS_DIRECTORY_PATH = tokenPath;
        this.CREDENTIALS_FILE_PATH = certFile;
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        getCredentials();
        driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }




    public String createFile(String folderId, byte[] data, MimeType type, String fileName)throws IOException{
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        fileMetadata.setParents(Collections.singletonList(folderId));
        File file = driveService.files().create(fileMetadata, new ByteArrayContent(type.getMimeType(), data))
                .setFields("id, parents")
                .execute();
        return file.getId();
    }


    /**
     *
     * @return folder id
     * @throws IOException
     */
    public String createFolder(String folderName) throws IOException{
        File fileMetadata = new File();
        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        File file = driveService.files().create(fileMetadata)
                .setFields("id")
                .execute();
        return file.getId();
    }
}
