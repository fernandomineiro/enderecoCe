package moduloEndereco.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CAD_AREA_ATUACAO_BAIRRO")
public class AreaAtuacaoBairro  extends EntityBase{
	
	
	@JoinColumns({
        @JoinColumn(name="CD_BAIRRO"),
        @JoinColumn(name="CD_CIDADE")
    })
    @ManyToOne(fetch = FetchType.LAZY)
	private Bairro bairro;

	@JoinColumn(name="ID_AREA_ATUACAO")
	@ManyToOne(fetch = FetchType.LAZY)
	private AreaAtuacao areaAtuacao;



	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public AreaAtuacao getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	
	
	
}
