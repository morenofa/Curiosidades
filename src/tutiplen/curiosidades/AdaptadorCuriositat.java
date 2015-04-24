package tutiplen.curiosidades;

import java.util.ArrayList;

import tutiplen.curiosidades.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class AdaptadorCuriositat extends ArrayAdapter<Curiositat> {

	Activity context;
	ArrayList<Curiositat> dades;

	public AdaptadorCuriositat(Activity context, ArrayList<Curiositat> dades) {
		super(context, R.layout.listitem_titol, dades);
		this.context = context;
		this.dades = dades;
	}

	public View getView( int position, View convertView, ViewGroup parent) {

		View item = convertView;
		ViewHolder holder;

		if(item == null ) {
			LayoutInflater inflater = context.getLayoutInflater();
			item = inflater.inflate(R.layout.listitem_titol, null);
			holder= new ViewHolder();
			holder.titol = (TextView) item.findViewById(R.id.LblTitol);
			//holder.enllac = (TextView) item.findViewById(R.id.LblSubTitol);
			item.setTag(holder);
		} else{
			holder = (ViewHolder) item.getTag();
		}

		holder.titol.setText(dades.get(position).getTitol());
		//holder.enllac.setText(dades.get(position).getId());

		return(item);
	}
}

class ViewHolder {
	TextView titol;
	TextView enllac;
}