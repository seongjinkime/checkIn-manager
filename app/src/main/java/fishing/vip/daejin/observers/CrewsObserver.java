package fishing.vip.daejin.observers;

import fishing.vip.daejin.checkinmanager.MainActivity;
import fishing.vip.daejin.interfaces.Observer;
import fishing.vip.daejin.model.Crews;

public class CrewsObserver implements Observer{
    MainActivity activity;

    public CrewsObserver(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void update(Crews crews) {
        this.activity.crewsChanged(crews);
    }
}
