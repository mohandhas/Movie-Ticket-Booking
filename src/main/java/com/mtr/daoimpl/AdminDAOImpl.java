package com.mtr.daoimpl;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mtr.dao.AdminDAO;
import com.mtr.mapper.AddonsMapper;
import com.mtr.mapper.AdminMapper;
import com.mtr.mapper.MoviesListInTheatreSQLMapper;
import com.mtr.mapper.MovieMapper;
import com.mtr.mapper.TheatreMapper;
import com.mtr.pojo.Addons;
import com.mtr.pojo.Admin;
import com.mtr.pojo.BookedTicketsForParticularShow;
import com.mtr.pojo.GetMovieInTheatre;
import com.mtr.pojo.Movie;
import com.mtr.pojo.MoviesListInTheatreSQL;
import com.mtr.pojo.MoviesListInTheatre;
import com.mtr.pojo.Theatre;
import com.mtr.pojo.MovieInTheatre;

public class AdminDAOImpl implements AdminDAO {

	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	private Admin findAdmin(String id) {
		Admin checker;
		try {
			String sql = "SELECT * FROM ADMIN WHERE ADMIN_ID=?";
			checker = jdbcTemplate.queryForObject(sql, new Object[] { id }, new AdminMapper());
		} catch (Exception e) {
			logger.error("User Name does not exsist!");
			checker = null;
		}

		if (checker == null) {
			return null;
		}
		logger.info("Admin's User Name found in DB");
		return checker;
	}

	public boolean login(String id, String password) {
		Admin admin = this.findAdmin(id);

		if (admin == null) {
			return false;
		} else if (id.equals(admin.getId()) && password.equals(admin.getPassword())) {
			logger.info("Admin Logged In");
			return true;
		}
		logger.error("User Name Password Doesn't match");
		return false;
	}

	public boolean addTheatre(Theatre theatre) {
		int checker;
		try {
			String sql = "INSERT INTO THEATRE(THEATRE_NAME, LATITUDE, LONGITUDE,NO_OF_SCREENS) VALUES(?,?,?,?)";
			checker = jdbcTemplate.update(sql, theatre.getName(), theatre.getLatitude(), theatre.getLongitude(),
					theatre.getNumberOfScreen());
		} catch (Exception e) {
			logger.error("Values not inserted." + "Reasons may be:" + "1.Trying to insert NULL values"
					+ "2.Trying to add a Theatre which is already in DB");
			checker = 0;
		}
		if (checker == 0) {
			return false;
		}
		logger.info("Theatre Added successfully!");
		return true;
	}

	@Override
	public boolean addAddon(Addons addon) {
		int checker;

		try {
			String sql = "INSERT INTO ADDONS(ITEM_NAME, COST) VALUES(?,?)";
			checker = jdbcTemplate.update(sql, addon.getItemName(), addon.getCost());
		} catch (Exception e) {
			checker = 0;
		}

		if (checker == 0) {
			return false;
		}
		return true;
	}

	private Movie findMovieId(String name, int duration) {
		Movie checker;
		try {
			String sql = "SELECT * FROM MOVIE WHERE MOVIE_NAME=? AND MOVIE_DURATION=?";
			checker = jdbcTemplate.queryForObject(sql, new Object[] { name, duration }, new MovieMapper());
			logger.info("Movie ID found");
		} catch (Exception e) {
			logger.error("More than one movie found");
			checker = null;
		}
		return checker;
	}

	public boolean addMovie(Movie movie) {
		int checker;

		try {
			System.out.println(movie);
			String sql = "INSERT INTO MOVIE(MOVIE_NAME, MOVIE_DURATION) VALUES(?,?)";
			checker = jdbcTemplate.update(sql, movie.getName(), movie.getDuration());
			Movie getId = findMovieId(movie.getName(), movie.getDuration());
			System.out.println(getId);

			if (getId == null) {
				logger.error("Movie Not addedSuccessfully!");
				return false;
			}

			for (int i = 0; i < movie.getGenre().size(); i++) {
				String sql2 = "INSERT INTO GENRE_MOVIE(MOVIE_ID, GENRE_ID) VALUES(?,?)";
				jdbcTemplate.update(sql2, getId.getId(), movie.getGenre().get(i));
			}
		} catch (Exception e) {
			checker = 0;
		}

		if (checker == 0) {
			logger.error("Movie Not addedSuccessfully!");
			return false;
		}
		logger.info("Movie Added Successfully!");
		return true;
	}

	public List<Theatre> getAllTheatres() {
		List<Theatre> checker;
		try {
			String sql = "SELECT * FROM THEATRE";
			checker = jdbcTemplate.query(sql, new TheatreMapper());
		} catch (Exception e) {
			checker = null;
		}
		if (checker == null) {
			logger.error("No theatres Found");
			return null;
		}
		logger.info("Sending the List of Theatres");
		return checker;
	}

	public List<Movie> getAllMovies() {

		List<Movie> checker;
		try {
			String sql = "SELECT * FROM MOVIE";
			checker = jdbcTemplate.query(sql, new MovieMapper());
		} catch (Exception e) {
			checker = null;
		}

		if (checker == null) {
			logger.error("No Movies Found!");
			return checker;
		}
		logger.info("Sending List Of Movies");
		return checker;
	}

	public List<Addons> getAllAddons() {
		List<Addons> checker;
		try {
			String sql = "SELECT * FROM ADDONS";
			checker = jdbcTemplate.query(sql, new AddonsMapper());
		} catch (Exception e) {
			checker = null;
		}

		if (checker == null) {
			logger.error("No Addons Found!");
			return checker;
		}
		logger.info("Sending List Of Addons");
		return checker;
	}

	private Movie getDuration(int id) {
		Movie checker;
		try {
			String sql = "SELECT * FROM MOVIE WHERE MOVIE_ID=?";
			checker = jdbcTemplate.queryForObject(sql, new Object[] { id }, new MovieMapper());
		} catch (Exception e) {
			checker = null;
		}

		if (checker == null) {
			logger.error("Movie not Found!");
			return null;
		}
		logger.info("Returning Movie Object!");
		return checker;
	}

	public boolean addMovieInTheatre(MovieInTheatre theatreMovie) {
		int checker;
		try {
			Time endTime = theatreMovie.getStartTime();
			Movie temp = getDuration(theatreMovie.getMovieId());

			if (temp == null) {
				logger.error("movie not added sucessfully!");
				return false;
			}

			long duration = temp.getDuration();
			LocalTime localtime = endTime.toLocalTime();
			localtime = localtime.plusMinutes(duration);
			Time finalTime = java.sql.Time.valueOf(localtime);

			String theatreMovieId = "T" + theatreMovie.getTheatreId() + "M" + theatreMovie.getMovieId();

			String sql = "INSERT INTO THEATRE_MOVIE(THEATRE_MOVIE_ID,THEATRE_ID, MOVIE_ID, SCREEN,DATE_FROM, DATE_TO,TIME_FROM,TIME_TO) VALUES(?,?,?,?,?,?,?,?)";
			checker = jdbcTemplate.update(sql, theatreMovieId, theatreMovie.getTheatreId(), theatreMovie.getMovieId(),
					theatreMovie.getScreen(), theatreMovie.getStartDate(), theatreMovie.getEndDate(),
					theatreMovie.getStartTime(), finalTime);

		} catch (Exception e) {
			checker = 0;
		}

		if (checker == 0) {
			logger.error("movie not added sucessfully!");
			return false;
		}
		logger.info("Movie Added Sucessfully!");
		return true;
	}

	@Override
	public List<MoviesListInTheatre> listMoviesInTheatre(GetMovieInTheatre getMoviesInTheatre) {

		List<MoviesListInTheatreSQL> checker;

		List<MoviesListInTheatre> moviesListInTheatre = new ArrayList<>();

		try {
			String sql = "SELECT * FROM MOVIE " + "JOIN THEATRE_MOVIE ON MOVIE.MOVIE_ID = THEATRE_MOVIE.MOVIE_ID "
					+ "JOIN GENRE_MOVIE ON MOVIE.MOVIE_ID=GENRE_MOVIE.MOVIE_ID "
					+ "WHERE THEATRE_MOVIE.THEATRE_ID =? and date_from<=? and date_to>=? "
					+ "ORDER BY THEATRE_MOVIE_ID ASC";
			checker = jdbcTemplate.query(sql, new Object[] { getMoviesInTheatre.getTheatreId(),
					getMoviesInTheatre.getShowDate(), getMoviesInTheatre.getShowDate() },
					new MoviesListInTheatreSQLMapper());

			for (int i = 0; i < checker.size(); i++) {
				MoviesListInTheatre temp = new MoviesListInTheatre();

				if (moviesListInTheatre.isEmpty()
						|| !((moviesListInTheatre.get(moviesListInTheatre.size() - 1).getTheatreMovieId())
								.equals(checker.get(i).getTheatreMovieId()))) {

					temp.setTheatreMovieId(checker.get(i).getTheatreMovieId());
					temp.setMovieId(checker.get(i).getMovieId());
					temp.setMovieName(checker.get(i).getMovieName());
					temp.setDuration(checker.get(i).getDuration());
					temp.setScreen(checker.get(i).getScreen());
					temp.setRating(checker.get(i).getRating());
					temp.setRatingCount(checker.get(i).getRatingCount());
					temp.setGenre(new LinkedHashSet<Integer>());
					temp.setShowTime(new LinkedHashSet<Time>());
					temp.getGenre().add(checker.get(i).getGenre());
					temp.getShowTime().add(checker.get(i).getShowTime());
					moviesListInTheatre.add(temp);
				} else {
					moviesListInTheatre.get(moviesListInTheatre.size() - 1).getGenre().add(checker.get(i).getGenre());
					moviesListInTheatre.get(moviesListInTheatre.size() - 1).getShowTime()
							.add(checker.get(i).getShowTime());
				}
			}

		} catch (Exception e) {
			System.out.println("In catch");
			checker = null;
		}

		if (checker == null) {
			logger.error("No movies found!");
			return null;
		}
		logger.info("The Selected Theatre contains the below movies");
		return moviesListInTheatre;
	}

	
	@Override
	public boolean editMovieInTheatre(MovieInTheatre theatreMovie) {
		int checker = 0;
		try {
			Time endTime = theatreMovie.getStartTime();
			Movie temp = getDuration(theatreMovie.getMovieId());
			long duration = temp.getDuration();
			LocalTime localtime = endTime.toLocalTime();
			localtime = localtime.plusMinutes(duration);
			Time finalTime = java.sql.Time.valueOf(localtime);

			String sql = "UPDATE THEATRE_MOVIE SET THEATRE_ID=?, MOVIE_ID=?, SCREEN=?,DATE_FROM=?, DATE_TO=?,TIME_FROM=?,TIME_TO=? WHERE THEATRE_MOVIE_ID=?";
			checker = jdbcTemplate.update(sql, theatreMovie.getTheatreId(), theatreMovie.getMovieId(),
					theatreMovie.getScreen(), theatreMovie.getStartDate(), theatreMovie.getEndDate(),
					theatreMovie.getStartTime(), finalTime, theatreMovie.getTheatreMovieId());
		} catch (Exception e) {
			checker = 0;
		}

		if (checker == 0) {
			logger.error("Enter proper details! Cannot edit!");
			return false;
		}
		logger.info("Edited movie in particular theatre");
		return true;
	}

	
	@Override
	public Integer getTicketCountInAParticularShow(BookedTicketsForParticularShow bookedTicketsForParticularShow) {

		String sql = "SELECT COUNT(*) FROM TICKET_BOOKING WHERE THEATRE_MOVIE_ID=? AND DATE=? AND SHOW_TIME=?";
		return jdbcTemplate.queryForObject(sql,
				new Object[] { bookedTicketsForParticularShow.getTheatreMovieId(),
						bookedTicketsForParticularShow.getShowDate(), bookedTicketsForParticularShow.getShowTime() },
				Integer.class);
	}

	@Override
	public boolean editAddonCost(Addons addon) {
		int checker = 0;
		try {
			String sql = "UPDATE ADDONS SET COST=? WHERE ITEM_NAME=?";
			checker = jdbcTemplate.update(sql, addon.getCost(),addon.getItemName()); 
		} catch (Exception e) {
			checker = 0;
		}

		if (checker == 0) {
			logger.error("Enter proper details! Cannot edit!");
			return false;
		}
		logger.info("Price Updated!-");
		return true;
	}
}
