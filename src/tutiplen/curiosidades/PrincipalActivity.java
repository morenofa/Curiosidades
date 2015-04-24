package tutiplen.curiosidades;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import tutiplen.curiosidades.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PrincipalActivity extends Activity implements OnItemClickListener {

	private ListView lstNoticies;
	ArrayList<Curiositat> llistaNoticies;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		CuriositatXmlParser noticiesParser = new CuriositatXmlParser();
		AssetManager am = this.getAssets();
		llistaNoticies = null;
		try {
			InputStream in = am.open("curiositats.xml");
			llistaNoticies = noticiesParser.parse(in);
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		AdaptadorCuriositat adaptador = new AdaptadorCuriositat(this ,llistaNoticies);
		lstNoticies = (ListView) findViewById(R.id.LstNoticies);
		lstNoticies.setAdapter(adaptador);

		lstNoticies.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		if (a.getAdapter().getItem(position) != null) {
			Intent i = new Intent(this, ShowActivity.class);
			i.putExtra("noticia", (Curiositat) a.getAdapter().getItem(position));
			startActivity(i);
		}
	}

}
