package pl.jcode.organizer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.jcode.organizer.Main;
import pl.jcode.organizer.domain.AlertBox;
import pl.jcode.organizer.domain.Event;

public class MainWindowController {

	private String newDate = "";
	private String newHour = "";
	private String newDesc = "";

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button saveButton;

	@FXML
	private TextField hourField;

	@FXML
	private TextField descField;

	@FXML
	private ListView<String> listView;

	public void initialize() {
		listView.setId(null);
	}

	public void getDatePicker() {
		try {
			newDate = datePicker.getValue().toString();
			System.out.println(newDate);
			showEventList(newDate);
		} catch (Exception e) {
			newDate = "";
			System.out.println("Wystapil wyjatek (data)\n" + e);
		}
	}

	public void saveEvent() {
		try {
			newHour = hourField.getText();
		} catch (Exception e) {
			newHour = "";
			System.out.println("Wystapil wyjatek (godzina)\n" + e);
		}
		try {
			newDesc = descField.getText();
		} catch (Exception e) {
			newDesc = "";
			System.out.println("Wystapil wyjatek (event)\n" + e);
		}

		if (newDate.equals("")) {
			AlertBox.display("B��d!", "Nie wybrano daty!");
		} else {
			if (newHour.equals("")) {
				AlertBox.display("B��d!", "Podaj godzine!");
			} else {
				if (newDesc.equals("")) {
					AlertBox.display("B��d!", "Opisz wydarzenie!");
				} else {
					// add event to database
					Event event = new Event();
					event.setDate(newDate);
					event.setHour(newHour);
					event.setEvent(newDesc);
					Main.entityManager.getTransaction().begin();
					Main.entityManager.persist(event);
					Main.entityManager.getTransaction().commit();

					// show result on ListView
					AlertBox.display("Sukces!", "Zapisano do bazy.");
					showEventList(newDate);

					// clear all fields
					hourField.setText("");
					descField.setText("");
					newHour = "";
					newDesc = "";
				}
			}
		}

	}

	public void showEventList(String date) {
		TypedQuery<Event> query = Main.entityManager.createQuery("SELECT e FROM Event e WHERE e.date = :date order by e.hour ASC", Event.class);
		query.setParameter("date", date);
		List<Event> events = query.getResultList();
		List<String> eventS = new ArrayList<>();
		for (Event event : events) {
			eventS.add(event.getHour() + ", " + event.getEvent());
		}
		ObservableList<String> items = FXCollections.observableArrayList(eventS);
		listView.setItems(items);
	}

}