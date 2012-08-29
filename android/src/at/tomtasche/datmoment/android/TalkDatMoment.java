package at.tomtasche.datmoment.android;


public class TalkDatMoment {

    @SuppressWarnings("unused")
    private static void dontCallMeMaybe() {
//	Cursor cursor = null;
//	List<Contact> contacts = new ArrayList<Contact>();
//	try {
//	    cursor = getContentResolver().query(Email.CONTENT_URI, null,
//		    Email.MIMETYPE + " = ? AND " +
//		    // Email.PRESENCE + " NOT NULL AND " +
//			    Email.ADDRESS + " NOT NULL", new String[] { Email.CONTENT_ITEM_TYPE },
//		    Email.LAST_TIME_CONTACTED + " DESC");
//	    while (cursor.moveToNext()) {
//		if (cursor.getString(cursor.getColumnIndex(Email.PRESENCE)) != null) {
//		    String name = cursor.getString(cursor
//			    .getColumnIndex(Email.DISPLAY_NAME_PRIMARY));
//		    String address = cursor.getString(cursor.getColumnIndex(Email.ADDRESS));
//
//		    contacts.add(new Contact(name, address));
//		}
//	    }
//	} finally {
//	    if (cursor != null)
//		cursor.close();
//	}
//	
//	spinnerIntent = (Spinner) findViewById(R.id.spinnerIntent);
//	spinnerIntent.setAdapter(new ArrayAdapter<Contact>(this,
//		android.R.layout.simple_list_item_1, android.R.id.text1, contacts));
//	findViewById(R.id.buttonShare).setOnClickListener(new OnClickListener() {
//
//	    @Override
//	    public void onClick(View v) {
//		Uri imUri = new Uri.Builder().scheme("imto").authority("gtalk")
//			.appendPath(((Contact) spinnerIntent.getSelectedItem()).address)
//			.query("message;body=bussi")
//			.appendQueryParameter("body", "bussi")
//			.build();
//		System.out.println(imUri.toString());
//		Intent imIntent = new Intent(Intent.ACTION_SENDTO, imUri);
//		imIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "bussi bussi :-*");
//		imIntent.putExtra(Intent.EXTRA_TEXT, "bussi bussi bussi :-* :)");
//		startActivity(imIntent);
//	    }
//	});
//
//	List<Intent> targetedShareIntents = new ArrayList<Intent>();
//	Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
//	shareIntent.setType("text/plain");
//	List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(shareIntent, 0);
//	if (!resInfo.isEmpty()) {
//	    for (ResolveInfo resolveInfo : resInfo) {
//		String packageName = resolveInfo.activityInfo.packageName;
//		Intent targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
//		targetedShareIntent.setType("text/plain");
//		targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
//			"bussi bussi :-*");
//		targetedShareIntent.putExtra(Intent.EXTRA_TEXT, "bussi bussi bussi :-* :)");
//		targetedShareIntent.setPackage(packageName);
//		targetedShareIntents.add(targetedShareIntent);
//	    }
//	}
//	
//	targetedShareIntents.add(imIntent);
//
//	Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0),
//		"Quick! Share dat moment");
//	chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
//		targetedShareIntents.toArray(new Parcelable[] {}));
//
//	startActivity(chooserIntent);
    }
//
//    private class Contact {
//
//	String name;
//
//	String address;
//
//	public Contact(String name, String address) {
//	    this.name = name;
//	    this.address = address;
//	}
//
//	@Override
//	public String toString() {
//	    return name;
//	}
//    }
}
