package prj5;

import java.util.Comparator;


public class InfluencerNameComparator implements Comparator<Influencer> {

        @Override
        public int compare(Influencer o1, Influencer o2) {
            
        
            return ((Object)o1.getChannelName()).compareToIgnoreCase(o2.getChannelName());
        }


}