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
		
		//Data �ʱ�ȭ
		onCreateData();
		
		//ExpandableListView�� ã�´�.
		ExpandableListView exList = (ExpandableListView)findViewById(R.id.expandablelist);
		//������ Type�� ������ List Map�� �����Ѵ�.
		List<Map<String, String>> dicType = new ArrayList<Map<String, String>>();
		//������ data�� ������ List Map�� �����Ѵ�. List�ȿ� List�ȿ� Map�� ���¸� �ڴ�.
		List<List<Map<String, String>>> dicArrName = new ArrayList<List<Map<String, String>>>();
		
		//List�� Map�� key�� �����͸� �����ϱ� ���� �ڵ�
		for(int i=0;i<arrData.size();i++) {
			Map<String, String> type = new HashMap<String, String>();
			//key���� �����͸� ����
			type.put("Type", arrData.get(i).Type);
			//dicType List�� HashMap�� ����
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
			dicType, //ȭ�鿡 �ѷ��� �����͸� ȣ��
			//����� ����Ʈ�並 ȣ��
			android.R.layout.simple_expandable_list_item_1,
			new String[] { "Type" }, //�ѷ��� ���� Hash�� key�� �����ش�.
			new int[] { android.R.id.text1 }, //�ѷ��� TextView�� �ҷ��´�.
			//����� ���� �����͸� ȣ���Ѵ�.
			dicArrName,
			android.R.layout.simple_expandable_list_item_2,
			//�迭�� ����Ͽ� ȣ�� �� �� �ִ�. �� ��� View�� ���� �� �°� �����ؾ� �Ѵ�.
			new String[] { "enName", "koName" },
			//String�� ���� View�� ���� 1:1�̾�� �Ѵ�.
			new int[] { android.R.id.text1, android.R.id.text2 }
		);
		//������ adapter�� List�� �ѷ��ش�.
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
		dic1.add(new DicName("ably", "�Ǹ���"));
		dic1.add(new DicName("abroad", "�ܱ���"));
		dic1.add(new DicName("absorptivity", "��� ���"));
		arrData.add(new DicType("a", dic1));
		
		List<DicName> dic2 = new ArrayList<DicName>();
		dic2.add(new DicName("Dahlia", "�޸��� ��"));
		dic2.add(new DicName("dance", "���ߴ�"));
		dic2.add(new DicName("dancing", "��"));
		dic2.add(new DicName("deadline", "���� �� ����"));
		arrData.add(new DicType("d", dic2));
		
		List<DicName> dic3 = new ArrayList<DicName>();
		dic3.add(new DicName("effect", "ȿ��"));
		dic3.add(new DicName("either", "�� �� �ϳ�"));
		dic3.add(new DicName("eligible", "���� ����[��] �� �ִ�"));
		dic3.add(new DicName("effective", "ȿ������"));
		dic3.add(new DicName("early", "������"));
		arrData.add(new DicType("e", dic3));
	}

}
