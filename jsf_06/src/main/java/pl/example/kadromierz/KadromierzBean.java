package pl.example.kadromierz;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class KadromierzBean implements Serializable {

    @Inject private UserService userService;
    @Inject private ScheduleService scheduleService;
    @Inject private ShiftService shiftService;

    private Integer selectedUserId;

    private Schedule newSchedule = new Schedule();
    private Schedule lastAddedSchedule;

    public List<User> getUsers() { return userService.findAll(); }

    /** Odczyt parametru GET ?userId=... oraz zapis do sesji. */
    public String selectUserFromGet() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> params = ec.getRequestParameterMap();
        String idStr = params.get("userId");
        if (idStr != null && !idStr.isBlank()) {
            selectedUserId = Integer.parseInt(idStr);

            // HttpServletRequest/session (wymóg)
            HttpServletRequest req = (HttpServletRequest) ec.getRequest();
            req.getSession().setAttribute("selectedUserId", selectedUserId);
        }
        return "/secure/user.xhtml?faces-redirect=true";
    }

    public User getSelectedUser() {
        if (selectedUserId == null) return null;
        return userService.find(selectedUserId);
    }

    public List<Schedule> getSchedules() {
        if (selectedUserId == null) return List.of();
        return scheduleService.findByUser(selectedUserId);
    }

    public List<Shift> getShifts() {
        if (selectedUserId == null) return List.of();
        return shiftService.findByUser(selectedUserId);
    }

    /** POST: dodanie grafiku + przekazanie obiektu przez request scope. */
    public String addSchedule() {
        if (selectedUserId == null) return "/secure/user.xhtml?faces-redirect=true";
        lastAddedSchedule = scheduleService.add(selectedUserId, newSchedule);

        FacesContext.getCurrentInstance().getExternalContext()
                .getRequestMap().put("lastAddedSchedule", lastAddedSchedule);

        newSchedule = new Schedule();
        return "/secure/confirm.xhtml"; // forward -> requestScope działa
    }

    public String startShift() {
        if (selectedUserId == null) return "/secure/user.xhtml?faces-redirect=true";
        shiftService.startShift(selectedUserId);
        return "/secure/user.xhtml?faces-redirect=true";
    }

    public String endShift(int shiftId) {
        shiftService.endShift(shiftId);
        return "/secure/user.xhtml?faces-redirect=true";
    }

    public Integer getSelectedUserId() { return selectedUserId; }
    public void setSelectedUserId(Integer selectedUserId) { this.selectedUserId = selectedUserId; }

    public Schedule getNewSchedule() { return newSchedule; }
    public void setNewSchedule(Schedule newSchedule) { this.newSchedule = newSchedule; }

    public Schedule getLastAddedSchedule() { return lastAddedSchedule; }
}
