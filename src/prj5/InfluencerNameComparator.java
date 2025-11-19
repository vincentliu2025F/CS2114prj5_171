package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * Creates comparator meant for comparing influencer names against each other
 * using the string compare.
 * 
 * @author vincentliu
 * @version Nov 19, 2025
 */
public class InfluencerNameComparator
    implements Comparator<Influencer>
{

    @Override
    public int compare(Influencer o1, Influencer o2)
    {
        return o1.getChannelName().compareToIgnoreCase(o2.getChannelName());
    }

}
