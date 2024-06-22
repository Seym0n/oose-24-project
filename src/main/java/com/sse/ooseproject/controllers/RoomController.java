package com.sse.ooseproject.controllers;

import com.sse.ooseproject.RoomRepository;
import com.sse.ooseproject.models.Employee;
import com.sse.ooseproject.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomController {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomController(RoomRepository pRoomRepository){
        this.roomRepository = pRoomRepository;
    }

    @GetMapping("/rooms")
    public String rooms(Model model, @RequestParam(name = "sort_by", required = false, defaultValue = "id") String sortBy,
                        @RequestParam(name = "sort_asc", required = false, defaultValue = "true") boolean sortAsc) {

        Sort sort = sortAsc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        List<Room> rooms = this.roomRepository.findAll(sort);
        model.addAttribute("rooms", rooms);
        model.addAttribute("sort_by", sortBy);
        model.addAttribute("sort_asc", sortAsc);

        // Returning the name of a view (found in resources/templates) as a string lets this endpoint return that view.
        return "rooms";
    }
}
