package pso.rap.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "bands")
public class Band {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String name;
	private String origin;
	private String genres;
	private String website;

	private String topsonglink;

	@OneToOne(mappedBy = "band")
	@JsonBackReference
	private BandSummary bandSummary;

	@Version
	private long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public BandSummary getBandSummary() {
		return bandSummary;
	}

	public void setBandSummary(BandSummary bandSummary) {
		this.bandSummary = bandSummary;
	}

	public String getTopsonglink() {
		return topsonglink;
	}

	public void setTopsonglink(String topSongLink) {
		this.topsonglink = topSongLink;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
