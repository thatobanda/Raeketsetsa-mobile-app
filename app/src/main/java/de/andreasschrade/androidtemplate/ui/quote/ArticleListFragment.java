public class ArticleListFragment extends ListFragment {

    private Callback callback = dummyCallback;
    private  ImageView imageView;

    /**
     * A callback interface. Called whenever a item has been selected.
     */
    public interface Callback {
        void onItemSelected(String id);
    }

    /**
     * A dummy no-op implementation of the Callback interface. Only used when no active Activity is present.
     */
    private static final Callback dummyCallback = new Callback() {
        @Override
        public void onItemSelected(String id) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setListAdapter(new MyListAdapter());

        setListAdapter((ListAdapter) new EventAdapter());

        setHasOptionsMenu(true);







    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // notify callback about the selected list item
        callback.onItemSelected(Event.ITEMS.get(position).id);
    }

    /**
     * onAttach(Context) is not called on pre API 23 versions of Android.
     * onAttach(Activity) is deprecated but still necessary on older devices.
     */
    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /**
     * Deprecated on API 23 but still necessary for pre API 23 devices.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /**
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        if (!(context instanceof Callback)) {
            throw new IllegalStateException("Activity must implement callback interface.");
        }

        callback = (Callback) context;
    }

/**  public  class EventAdapter extends BaseAdapter {




        @Override
        public int getCount() {
            return Event.ITEMS.size();
        }

        @Override
        public Object getItem(int position) {
            return Event.ITEMS.get(position);
        }

        @Override
        public long getItemId(int position) {
            return Event.ITEMS.get(position).id.hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_article, container, false);
            }

            final Event item = (Event) getItem(position);
            ((TextView) convertView.findViewById(R.id.article_title)).setText(item.getAuthor());
            ((TextView) convertView.findViewById(R.id.article_subtitle)).setText(item.getTitle());
            final ImageView img = (ImageView) convertView.findViewById(R.id.thumbnail);
            Glide.with(getActivity()).load(item.getPhoto()).asBitmap().into(new BitmapImageViewTarget(img) {

            });

            return convertView;
        }
    }   */

    public ArticleListFragment() {
    }
}
