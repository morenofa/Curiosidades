package tutiplen.curiosidades;

import tutiplen.curiosidades.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		
		Bundle extras = getIntent().getExtras();
		TextView titol = (TextView) this.findViewById(R.id.titol);
		TextView text = (TextView) this.findViewById(R.id.text);

		if (null != extras) {
			Curiositat n = (Curiositat) extras.getSerializable("noticia");

			titol.setText(n.getTitol().toString());
			text.setText(n.getText().toString());
		}
	}

}
