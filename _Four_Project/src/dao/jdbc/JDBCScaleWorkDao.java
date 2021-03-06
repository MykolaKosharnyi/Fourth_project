package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.ScaleWork;
import dao.ScaleWorkDao;

public class JDBCScaleWorkDao implements ScaleWorkDao {

	@Override
	public void create(ScaleWork scaleWork) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement(
							"INSERT INTO scalework (size, scale) values (?, ?)",
							Statement.RETURN_GENERATED_KEYS);
					st.setString(1, scaleWork.name());
					st.setInt(2, scaleWork.getScale());
					st.executeUpdate();

				} finally {
					if (st != null)
						st.close();
					st = null;
				}
			} finally {
				if (cn != null)
					cn.close();
				cn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public int find(String name) {
		int id = 0;
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM scalework WHERE size = ?");
					st.setString(1, name);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						if (rs.next()) {
							id = rs.getInt(1);
						}
					} finally {
						if (rs != null)
							rs.close();
						rs = null;
					}
				} finally {
					if (st != null)
						st.close();
					st = null;
				}
			} finally {
				if (cn != null)
					cn.close();
				cn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public ScaleWork find(int id) {
		ScaleWork scaleWork = null;
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM scalework WHERE ID = ?");
					st.setInt(1, id);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						if (rs.next()) {
							scaleWork = ScaleWork.valueOf(rs.getString(2));
						}
					} finally {
						if (rs != null)
							rs.close();
						rs = null;
					}
				} finally {
					if (st != null)
						st.close();
					st = null;
				}
			} finally {
				if (cn != null)
					cn.close();
				cn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scaleWork;
	}

	@Override
	public List<ScaleWork> findAll() {
		List<ScaleWork> scaleWork = new ArrayList<ScaleWork>();

		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				Statement st = null;
				try {
					st = cn.createStatement();
					ResultSet rs = null;
					try {
						rs = st.executeQuery("SELECT * FROM scalework ");
						while (rs.next()) {
							scaleWork.add(ScaleWork.valueOf(rs.getString(2)));
						}
					} finally {
						if (rs != null)
							rs.close();
						rs = null;
					}
				} finally {
					if (st != null)
						st.close();
					st = null;
				}
			} finally {
				if (cn != null)
					cn.close();
				cn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scaleWork;
	}

	@Override
	public void delete(int id) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM scalework WHERE id = ?");
					st.setInt(1, id);
					st.executeUpdate();

				} finally {
					if (st != null)
						st.close();
					st = null;
				}
			} finally {
				if (cn != null)
					cn.close();
				cn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
