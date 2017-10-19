package assaf.zfani.parsnytimes;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by user on 10/19/2017.
 */

public class NyTimesAsync extends AsyncTask<String,String,Void> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        


    }

    @Override
    protected Void doInBackground(String... strings) {
        try{
            URL nyt = new URL(strings[0]);
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(nyt.openStream()));
            int current = xmlPullParser.getEventType();
            int counter=0;
            String currentTitle;
            boolean title=false;
            while (current!=XmlPullParser.END_DOCUMENT)
            {
                if (title&&current==XmlPullParser.TEXT)
                {
                    if(counter>1) {
                        currentTitle = xmlPullParser.getText();
                        publishProgress(currentTitle);
                    }
                    counter++;
                }

                if (current==XmlPullParser.START_TAG)
                {
                    if (xmlPullParser.getName().equals("title"))
                    {
                        title = true;
                    }
                }
                if (current==XmlPullParser.END_TAG)
                {
                    if (xmlPullParser.getName().equals("title"))
                    {
                        title=false;
                    }
                }
            }
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
        return null;
    }
}
