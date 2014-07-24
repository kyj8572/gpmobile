package net.thdev.expandableexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class MainActivity extends Activity {
	List<DicType> arrData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		arrData = new ArrayList<DicType>();
		
		//Data 초기화
		onCreateData();
		
		//ExpandableListView를 찾는다.
		ExpandableListView exList = (ExpandableListView)findViewById(R.id.expandablelist);
		//사전의 Type을 저장할 List Map을 생성한다.
		List<Map<String, String>> dicType = new ArrayList<Map<String, String>>();
		//사전의 data를 저장할 List Map을 생성한다. List안에 List안에 Map의 형태를 뛴다.
		List<List<Map<String, String>>> dicArrName = new ArrayList<List<Map<String, String>>>();
		
		//List의 Map에 key와 데이터를 저장하기 위한 코드
		for(int i=0;i<arrData.size();i++) {
			Map<String, String> type = new HashMap<String, String>();
			//key값과 데이터를 저장
			type.put("Type", arrData.get(i).Type);
			//dicType List에 HashMap을 저장
			dicType.add(type);
			
			List<Map<String, String>> arrName = new ArrayList<Map<String, String>>();
			for(int j=0;j<arrData.get(i).Array.size();j++) {
				Map<String, String> name = new HashMap<String, String>();
				name.put("enName", arrData.get(i).Array.get(j).enName);
				name.put("koName", arrData.get(i).Array.get(j).koName);
				arrName.add(name);
			}
			dicArrName.add(arrName);
		}
		
		ExpandableListAdapter adapter = new SimpleExpandableListAdapter(
			this,
			dicType, //화면에 뿌려줄 데이터를 호출
			//사용할 리스트뷰를 호출
			android.R.layout.simple_expandable_list_item_1,
			new String[] { "Type" }, //뿌려줄 값의 Hash의 key를 적어준다.
			new int[] { android.R.id.text1 }, //뿌려줄 TextView를 불러온다.
			//사용할 보조 데이터를 호출한다.
			dicArrName,
			android.R.layout.simple_expandable_list_item_2,
			//배열을 사용하여 호출 할 수 있다. 이 경우 View의 수와 꼭 맞게 적용해야 한다.
			new String[] { "enName", "koName" },
			//String의 수와 View의 수가 1:1이어야 한다.
			new int[] { android.R.id.text1, android.R.id.text2 }
		);
		//생성한 adapter를 List에 뿌려준다.
		exList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	protected void onCreateData() {
		List<DicName> dic1 = new ArrayList<DicName>();
		dic1.add(new DicName("ably", "훌륭히"));
		dic1.add(new DicName("abroad", "외국에"));
		dic1.add(new DicName("absorptivity", "흡수 계수"));
		arrData.add(new DicType("a", dic1));
		
		List<DicName> dic2 = new ArrayList<DicName>();
		dic2.add(new DicName("Dahlia", "달리아 꽃"));
		dic2.add(new DicName("dance", "춤추다"));
		dic2.add(new DicName("dancing", "춤"));
		dic2.add(new DicName("deadline", "넘을 수 없는"));
		arrData.add(new DicType("d", dic2));
		
		List<DicName> dic3 = new ArrayList<DicName>();
		dic3.add(new DicName("effect", "효과"));
		dic3.add(new DicName("either", "둘 중 하나"));
		dic3.add(new DicName("eligible", "…을 가질[할] 수 있는"));
		dic3.add(new DicName("effective", "효과적인"));
		dic3.add(new DicName("early", "조기의"));
		arrData.add(new DicType("e", dic3));
	}

}
