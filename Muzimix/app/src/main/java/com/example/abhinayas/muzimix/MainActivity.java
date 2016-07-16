package com.example.abhinayas.muzimix;


        import android.Manifest;
        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.media.AudioFormat;
        import android.media.AudioTrack;
        import android.media.MediaPlayer;
        import android.media.MediaRecorder;
        import android.net.Uri;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.support.v4.app.ActivityCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;

        import java.io.BufferedInputStream;
        import java.io.BufferedOutputStream;
        import java.io.Console;
        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.SequenceInputStream;
        import java.lang.reflect.Type;
        import java.util.Date;
        import java.util.Enumeration;
        import java.util.Vector;
//import javax.sound.sampled.AudioFileFormat;





public class MainActivity extends AppCompatActivity {



    public Button record = null;
    private MediaRecorder mRecorder = null;

    public Button play = null;
    public ImageView go;
    private MediaPlayer   mPlayer = null;
    public int mStartRecording = 1;
    public int mStartPlaying = 1;
    public AudioTrack newAudio=null;
    public double[][] sample=null;
    // Storage Permissions variables
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //persmission method.
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have read or write permission
        int writePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

        switch(permsRequestCode){

            case 200:

                boolean writeAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;

                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

       verifyStoragePermissions(this);


        go=(ImageView)findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    Intent i = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(i);
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

        });



    }

    private void mergeSongs(File mergedFile,File...mp3Files){
        FileInputStream fisToFinal = null;
        FileOutputStream fos = null;
        Toast.makeText(MainActivity.this,"mischeif..",Toast.LENGTH_SHORT).show();

        try {
            fos = new FileOutputStream(mergedFile);
            fisToFinal = new FileInputStream(mergedFile);
            Toast.makeText(MainActivity.this,"mischeif1..",Toast.LENGTH_SHORT).show();

            for(File mp3File:mp3Files){
                if(!mp3File.exists())
                    continue;
                FileInputStream fisSong = new FileInputStream(mp3File);
                SequenceInputStream sis = new SequenceInputStream(fisToFinal, fisSong);
                byte[] buf = new byte[1024];
                try {
                    for (int readNum; (readNum = fisSong.read(buf)) != -1;)
                        fos.write(buf, 0, readNum);
                } finally {
                    if(fisSong!=null){
                        fisSong.close();
                    }
                    if(sis!=null){
                        sis.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(fos!=null){
                    fos.flush();
                    fos.close();
                }
                if(fisToFinal!=null){
                    fisToFinal.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void happy(String args[]) throws Exception
    {
        System.out.println("enter the filename");
        int a,no,i,j=1;
        String str,start;
        int ch;
        Console c=System.console();
        str=c.readLine();
        System.out.println("enter the destination file size");
        start=c.readLine();
        a=Integer.parseInt(start);


        File fr=new File(j+str);
        if(!fr.exists())
        {
            System.out.println("error");
            System.exit(0);
        }
        FileOutputStream fos=new FileOutputStream((str));
        for(i=0,j=1;    ;)
        {
            FileInputStream f=new FileInputStream(j+str);


            for(i=0;   (ch=f.read())!=-1  ;++i)
                fos.write(ch);
            j++;
            File fq=new File(j+str);
            if(!(fq).exists())
            {
                System.out.println("process completed");
                System.exit(0);
            }

        }
    }
}
