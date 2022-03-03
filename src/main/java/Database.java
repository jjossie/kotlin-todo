import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class Database {

    private static Firestore instance;

    public static Firestore getDatabase() throws IOException {
        // Use the application default credentials
//        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(credentials)
//                .setProjectId("kotlin-productivity")
//                .build();

        if (instance == null){

            FileInputStream serviceAccount =
                    new FileInputStream("C:\\Users\\joelj\\Documents\\LocalDev\\kotlin-todo\\secret\\kotlin-productivity-firebase-adminsdk-fa1eq-ed7accbd34.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            instance = FirestoreClient.getFirestore();
        }
        return instance;

    }

    public Database() {}

}
