package com.tr2.instrument;

public class TrigMACDInstrument extends MACDInstrument{
	
	protected boolean upOrDown;
	public TrigMACDInstrument(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addPrice(Price price) {
		// TODO Auto-generated method stub
		super.addPrice(price);
		checkIndicator();
		
	}

	private void checkIndicator() {
		int index=macdList.size()-1;
		if(index>bigPeriod){
			if(macdList.get(index-1)<=0&&macdList.get(index)>0){
				upOrDown=true;
				setChanged();
				notifyObservers(upOrDown);
				
			}
			if(macdList.get(index-1)>=0&&macdList.get(index)<0){
				upOrDown=false;
				setChanged();
				notifyObservers(upOrDown);
				
			}
		}
		
		
	}

}
