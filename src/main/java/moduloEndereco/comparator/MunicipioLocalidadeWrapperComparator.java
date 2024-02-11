package moduloEndereco.comparator;

import java.util.Comparator;

import moduloEndereco.service.dto.MunicipioLocalidadeWrapper;

public class MunicipioLocalidadeWrapperComparator implements Comparator<MunicipioLocalidadeWrapper> {

	private final String campo;
	private final String ordem;

	public MunicipioLocalidadeWrapperComparator(String campo, String ordem) {
		super();
		this.campo = campo;
		this.ordem = ordem;
	}

	@Override
	public int compare(MunicipioLocalidadeWrapper o1, MunicipioLocalidadeWrapper o2) {
		int comp;
		if (campo.equals("")) {
			comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
			if (comp == 0) {
				comp = o1.getMunicipio().compareTo(o2.getMunicipio());
			}
			if (comp == 0) {
				comp = o1.getId().getCdLocalidade().compareTo(o2.getId().getCdLocalidade());
			}
			if (comp == 0) {
				comp = o1.getLocalidade().compareTo(o2.getLocalidade());
			}
			if (comp == 0) {
				comp = o1.getRegional().compareTo(o2.getRegional());
			}
			return comp;
		}
		if (campo.equals("cdMunicipio")) {
			if (ordem.equals("desc")) {
				comp = o2.getId().getCdMunicipio().compareTo(o1.getId().getCdMunicipio());
				if (comp == 0) {
					comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				}
				if (comp == 0) {
					comp = o1.getId().getCdLocalidade().compareTo(o2.getId().getCdLocalidade());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				if (comp == 0) {
					comp = o1.getRegional().compareTo(o2.getRegional());
				}
				return comp;
			} else {
				comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				if (comp == 0) {
					comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				}
				if (comp == 0) {
					comp = o1.getId().getCdLocalidade().compareTo(o2.getId().getCdLocalidade());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				if (comp == 0) {
					comp = o1.getRegional().compareTo(o2.getRegional());
				}
				return comp;
			}
		}
		if (campo.equals("municipio")) {
			if (ordem.equals("desc")) {
				comp = o2.getMunicipio().compareTo(o1.getMunicipio());
				if (comp == 0) {
					comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				}
				if (comp == 0) {
					comp = o1.getId().getCdLocalidade().compareTo(o2.getId().getCdLocalidade());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				if (comp == 0) {
					comp = o1.getRegional().compareTo(o2.getRegional());
				}
				return comp;
			} else {
				comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				if (comp == 0) {
					comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				}
				if (comp == 0) {
					comp = o1.getId().getCdLocalidade().compareTo(o2.getId().getCdLocalidade());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				if (comp == 0) {
					comp = o1.getRegional().compareTo(o2.getRegional());
				}
				return comp;
			}
		}
		if (campo.equals("cdLocalidade")) {
			if (ordem.equals("desc")) {
				comp = o2.getId().getCdLocalidade().compareTo(o1.getId().getCdLocalidade());
				if (comp == 0) {
					comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				}
				if (comp == 0) {
					comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				if (comp == 0) {
					comp = o1.getRegional().compareTo(o2.getRegional());
				}
				return comp;
			} else {
				comp = o1.getId().getCdLocalidade().compareTo(o2.getId().getCdLocalidade());
				if (comp == 0) {
					comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				}
				if (comp == 0) {
					comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				if (comp == 0) {
					comp = o1.getRegional().compareTo(o2.getRegional());
				}
				return comp;
			}
		}
		if (campo.equals("localidade")) {
			if (ordem.equals("desc")) {
				comp = o2.getLocalidade().compareTo(o1.getLocalidade());
				if (comp == 0) {
					comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				}
				if (comp == 0) {
					comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				}
				if (comp == 0) {
					comp = o1.getId().getCdLocalidade().compareTo(o2.getId().getCdLocalidade());
				}
				if (comp == 0) {
					comp = o1.getRegional().compareTo(o2.getRegional());
				}
				return comp;
			} else {
				comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				if (comp == 0) {
					comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				}
				if (comp == 0) {
					comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				}
				if (comp == 0) {
					comp = o1.getId().getCdLocalidade().compareTo(o2.getId().getCdLocalidade());
				}
				if (comp == 0) {
					comp = o1.getRegional().compareTo(o2.getRegional());
				}
				return comp;
			}
		}
		if (campo.equals("regional")) {
			if (ordem.equals("desc")) {
				comp = o2.getRegional().compareTo(o1.getRegional());
				if (comp == 0) {
					comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				}
				if (comp == 0) {
					comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				return comp;
			} else {
				comp = o1.getRegional().compareTo(o2.getRegional());
				if (comp == 0) {
					comp = o1.getId().getCdMunicipio().compareTo(o2.getId().getCdMunicipio());
				}
				if (comp == 0) {
					comp = o1.getMunicipio().compareTo(o2.getMunicipio());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				if (comp == 0) {
					comp = o1.getLocalidade().compareTo(o2.getLocalidade());
				}
				return comp;
			}
		}
		return 0;
	}

}
