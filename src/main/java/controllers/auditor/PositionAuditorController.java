
package controllers.auditor;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditorService;
import services.ConfigurationService;
import services.PositionService;
import controllers.AbstractController;
import domain.Auditor;
import domain.Position;

@Controller
@RequestMapping("/position/auditor")
public class PositionAuditorController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private AuditorService			auditorService;

	@Autowired
	private PositionService			positionService;


	@RequestMapping(value = "/listPosition", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Position> positions;
		final Auditor auditor = this.auditorService.findByPrincipal();

		Boolean assigned = false;

		if (auditor.getPosition() == null)
			positions = this.positionService.positionsNotAssignedAnyAuditor();
		else {
			positions = new ArrayList<>();
			positions.add(auditor.getPosition());
			assigned = true;
		}

		final String banner = this.configurationService.findConfiguration().getBanner();

		result = new ModelAndView("position/list");
		result.addObject("positions", positions);
		result.addObject("banner", banner);
		result.addObject("requestURI", "area/chapter/listAreas.do");
		result.addObject("pagesize", 5);
		result.addObject("assigned", assigned);
		result.addObject("AmILogged", true);
		result.addObject("AmInFinder", false);

		return result;
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView select(@RequestParam final int positionId) {
		ModelAndView result;
		final Position position = this.positionService.findOne(positionId);
		Assert.notNull(position);
		final Auditor auditor = this.auditorService.findByPrincipal();

		final Auditor auditorOwner = this.auditorService.findAuditorByPositionId(positionId);

		if (auditor.getPosition() != null || auditorOwner != null || position.getFinalMode() == false)
			result = new ModelAndView("redirect:listPosition.do");
		else
			try {
				auditor.setPosition(position);
				this.auditorService.save(auditor);
				result = new ModelAndView("redirect:listPosition.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("misc/error");
				final String banner = this.configurationService.findConfiguration().getBanner();
				result.addObject("banner", banner);
			}
		return result;

	}
}
