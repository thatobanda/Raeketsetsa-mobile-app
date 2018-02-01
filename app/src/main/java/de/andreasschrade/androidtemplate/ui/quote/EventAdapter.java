public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    public TextView title, author;
    private List<Event> List;
    private Event event;
    private ImageView photo;
    private Context context;

    public EventAdapter() {

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, author;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.article_title);
            author = (TextView) view.findViewById(R.id.article_subtitle);
            photo = (ImageView) view.findViewById(R.id.thumbnail);

        }


    }

    public EventAdapter(List<Event> List) {
        this.List = List;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_article, parent, false);
        context = parent.getContext();


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Event e = List.get(position);
        holder.title.setText(e.getTitle());
        holder.author.setText(e.getAuthor());
        String img = e.getPhoto();

        Glide.with(context)
                .load(img)
                .into(photo);


    }


    @Override
    public int getItemCount() {
        return List.size();
    }

}
