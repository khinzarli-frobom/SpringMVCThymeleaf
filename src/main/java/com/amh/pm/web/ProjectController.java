package com.amh.pm.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amh.pm.entity.Organization;
import com.amh.pm.entity.Project;
import com.amh.pm.service.OrganizationService;
import com.amh.pm.service.ProjectService;

@Controller
public class ProjectController {
    HttpSession session;

    private ProjectService projectService;

    private OrganizationService organizationService;

    @Autowired(required = true)
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired(required = true)
    @Qualifier(value = "organizationService")
    public void setOrganizaitionService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/organizations/{id}/projects", method = RequestMethod.GET)
    public String showProjectLists(@PathVariable("id") int organizationId, Model model, HttpServletRequest request) {
        session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        } else {
            int userId = (Integer) session.getAttribute("userId");
            Organization organizationOwner = organizationService.findById(organizationId);
            if (userId == organizationOwner.getOwner().getId()) {
                session = request.getSession(true);
                session.setAttribute("organizationId", organizationId);
                List<Project> projects = projectService.findAll();
                List<Project> projectNames = new ArrayList<Project>();
                for (Project project : projects) {
                    if (project.getManager().getId() == organizationId) {
                        projectNames.add(project);
                    }
                }
                model.addAttribute("organizationId", organizationId);
                model.addAttribute("projectNames", projectNames);
                return "projectLists";
            } else {
                List<Project> projects = projectService.findAll();
                List<Project> projectNames = new ArrayList<Project>();
                for (Project project : projects) {
                    if (project.getManager().getId() == organizationId) {
                        projectNames.add(project);
                    }
                }
                model.addAttribute("projectNames", projectNames);
                return "showProjectLists";
            }
        }
    }

    @RequestMapping(value = "/organizations/{id}/projects/new", method = RequestMethod.GET)
    public String showProjectForm(@PathVariable("id") int organizationId, Model model, HttpServletRequest request) {
        session = request.getSession(false);
        if (session == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("organizationId", organizationId);
            model.addAttribute("project", new Project());
            return "addProject";
        }
    }

    @RequestMapping(value = "/organizations/{id}/projects/new", method = RequestMethod.POST)
    public String createProjects(@PathVariable("id") int organizationId, @Validated @ModelAttribute Project project, BindingResult result, Model model,
            HttpServletRequest request) {
        if (result.hasErrors()) {
            // if validator failed
            model.addAttribute("organizationId", organizationId);
            return "addProject";
        } else {
            if (project.getScheduleStartDate() == null || project.getScheduleEndDate() == null || project.getActualStartDate() == null || project.getActualEndDate() == null) {
                String dateEmptyError = "Please Fill All Dates";
                model.addAttribute("organizationId", organizationId);
                model.addAttribute("dateEmptyError", dateEmptyError);

                return "addProject";

            } else {
                Organization organization = organizationService.findById(organizationId);
                project.setManager(organization);
                Date scheduleStartDate = project.getScheduleStartDate();
                Date scheduleEndDate = project.getScheduleEndDate();
                Date actualStartDate = project.getActualStartDate();
                Date actualEndDate = project.getActualEndDate();

                if (scheduleStartDate.compareTo(scheduleEndDate) > 0) {
                    String dateError = "Schedule End Date Must Be Greater Than Schedule Start Date";
                    model.addAttribute("scheduleEndDate", dateError);
                    return "addProject";
                } else if (actualStartDate.compareTo(actualEndDate) > 0) {
                    String dateError = "Actual End Date Must Be Greater Than Actual Start Date";
                    model.addAttribute("actualEndDate", dateError);
                    return "addProject";
                } else if (scheduleStartDate.compareTo(actualStartDate) > 0) {
                    String dateError = "Actual Start Date Must Be Greater Than Schedule Start Date";
                    model.addAttribute("actualStartDate", dateError);
                    return "addProject";
                } else {
                    projectService.save(project);
                }
                showProjectLists(organizationId, model, request);
                return "projectLists";

            }
        }
    }
}
