package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Contato;
import vo.Operadora;

public class OperadoraDAO {
	
	Connection con;
	PreparedStatement ps;
	
	public boolean inserir(Operadora o) throws SQLException{
		
		//declara o sql
		String sql = " INSERT INTO operadora VALUES (0, ?, ?); ";
		
		//obtem a conexão
		con = ConnectionDB.getConnection();
		
		//prepara o sql
		ps = con.prepareStatement(sql);
		ps.setInt(1, o.getCodigo());
		ps.setString(2, o.getNome());
		
		//executa o sql e retorna o resultado
		return ps.executeUpdate() > 0;
		
	}
	
	public List<Operadora> listarTodas(){
		
		try {
			String sql = " SELECT * FROM operadora ";
			con = ConnectionDB.getConnection();
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Operadora> operadoras = new ArrayList<>();
			while(rs.next()){
				Operadora op = new Operadora();
				op.setCodOperadora(rs.getInt("cod_operadora"));
				op.setNome(rs.getString("nome"));
				op.setCodigo(rs.getInt("codigo"));
				
			}
			return operadoras;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			
	}

}
