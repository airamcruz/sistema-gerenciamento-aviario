package br.com.airamcruz.projeto.integrador.util.components;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabelaModel extends AbstractTableModel {

	private String[] _colunas = null;
	private List<Object[]> _linhas = null;
	
	public TabelaModel(String[] colunas, List<Object[]> linhas) 
	{
		setColunas(colunas);
		setLinhas(linhas);
	}
	
	public List<Object[]> getLinhas() {
		return _linhas;
	}
	
	public void setLinhas(List<Object[]> _linhas) {
		this._linhas = _linhas;
	}
	
	public String[] getColunas() {
		return _colunas;
	}
	
	public void setColunas(String[] _colunas) {
		this._colunas = _colunas;
	}

	
	@Override
	public int getRowCount() {
		return this._linhas.size();
	}

	@Override
	public int getColumnCount() {
		return this._colunas.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return _colunas[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object[] linha = getLinhas().get(rowIndex);
		return linha[columnIndex];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Object[] linha = getLinhas().get(rowIndex);
		linha[columnIndex] = aValue;
        fireTableRowsUpdated(rowIndex, columnIndex);
	}
	
	public void addRow(Object[] row)
	{
		this._linhas.add(row);
		this.fireTableDataChanged();
	}
	
	public void clear()
	{
		this._linhas.clear();
		this.fireTableDataChanged();
	}
	
	public void removeRow(int rowIndex)
	{
		this._linhas.remove(rowIndex);
		this.fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public int getIdRowSelected(int rowIndex)
	{

		Object[] linha = getLinhas().get(rowIndex);
		return (int)linha[0];
	}

}
