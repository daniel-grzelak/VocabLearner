package daniel.grzelak.models;



import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.daniel.classes.WordsAll;

public class CustomTableModelUser extends AbstractTableModel{

	private List<WordsAll> words;
	private List<String> headers;
	
	public CustomTableModelUser(List<WordsAll> words, List<String> headers) {
		this.words = words;
		this.headers = headers;
	}
	
	public void update(List<WordsAll> words) {
		this.words = words;
	}
	public int getRowCount() {
		// TODO Auto-generated method stub
		return words.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		WordsAll w = words.get(rowIndex);
		
		if(columnIndex == 0) {
			return w.getId();
		}else if (columnIndex == 1) {
			return w.getWordPl();
		}else if (columnIndex == 2) {
			return w.getWordEng();
		}else  {
			return w.getInterval();
		}
	}

	@Override
	public String getColumnName(int column) {
		return headers.get(column);
	}
}
