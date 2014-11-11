package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import course.labs.todomanager.ToDoItem.Status;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}
	
	static class ViewHolder {
		  TextView title;
		  TextView statusLabel;
		  CheckBox statusCheckBox;
		  TextView priorityLabel;
		  TextView priorityView;
		  TextView dateLabel;
		  TextView dateView;
		}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		// TODO - Get the current ToDoItem
		final ToDoItem toDoItem = mItems.get(position);
		// TODO - Inflate the View for this ToDoItem
		// from todo_item.xml

		// TODO - Fill in specific ToDoItem data
		// Remember that the data that goes in this View
		// corresponds to the user interface elements defined
		// in the layout file
		
		if(convertView==null){

	        // inflate the layout
			LayoutInflater mInflater;
			mInflater = LayoutInflater.from(mContext);
	        convertView = mInflater.inflate(R.layout.todo_item, parent, false);

	        // well set up the ViewHolder
	        viewHolder = new ViewHolder();
	        viewHolder.title = (TextView) convertView.findViewById(R.id.titleView);
	        viewHolder.statusLabel = (TextView) convertView.findViewById(R.id.StatusLabel);
	        viewHolder.statusCheckBox = (CheckBox) convertView.findViewById(R.id.statusCheckBox);
	        viewHolder.priorityView = (TextView) convertView.findViewById(R.id.priorityView);
	        viewHolder.priorityLabel = (TextView) convertView.findViewById(R.id.PriorityLabel);
	        viewHolder.dateView = (TextView) convertView.findViewById(R.id.dateView);
	        viewHolder.dateLabel = (TextView) convertView.findViewById(R.id.DateLabel);
	        // store the holder with the view.
	        convertView.setTag(viewHolder);

	    }else{
	        // we've just avoided calling findViewById() on resource everytime
	        // just use the viewHolder
	        viewHolder = (ViewHolder) convertView.getTag();
	    }
		
		RelativeLayout itemLayout = (RelativeLayout) convertView;

		// TODO - Display Title in TextView
		final TextView titleView = viewHolder.title;
		titleView.setText(toDoItem.getTitle());
		// TODO - Set up Status CheckBox
		final CheckBox statusView = viewHolder.statusCheckBox;
		Status status = toDoItem.getStatus();
		if (status == Status.DONE) {
			statusView.setChecked(true);
		}
		else {
			statusView.setChecked(false);
		}

		statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Log.i(TAG, "Entered onCheckedChanged()");

				// TODO - set up an OnCheckedChangeListener, which
				// is called when the user toggles the status checkbox

			}
		});

		// TODO - Display Priority in a TextView

		final TextView priorityView = viewHolder.priorityView;
		priorityView.setText(toDoItem.getPriority().toString());
		// TODO - Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
		// time String

		final TextView dateView = viewHolder.dateView;
		dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));
		// Return the View you just created
		return itemLayout;

	}
}
