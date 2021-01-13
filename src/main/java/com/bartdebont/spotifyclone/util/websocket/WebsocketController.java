package com.bartdebont.spotifyclone.util.websocket;

import com.bartdebont.spotifyclone.model.Customer;
import com.bartdebont.spotifyclone.service.CustomerService;
import com.bartdebont.spotifyclone.service.PlaylistService;
import com.bartdebont.spotifyclone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.DestinationVariable;

import java.text.MessageFormat;

@CrossOrigin(origins = {"*"})
@RestController
public class WebsocketController {

    private PlaylistService playlistService;

    private CustomerService customerService;

    private JwtUtil jwtUtil;

    @Autowired
    private SimpMessagingTemplate sendingOperations;

    @Autowired
    public WebsocketController(PlaylistService playlistService, CustomerService customerService, JwtUtil jwtUtil) {
        this.playlistService = playlistService;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @EventListener
    public void handleWebSocketConnectionListener(final SessionConnectedEvent event) {
        System.out.println("Connected Console");
    }

    @EventListener
    public void handleWebSocketDisconnectionListener(final SessionDisconnectEvent event) {
        System.out.println("disconnected");
    }

    @MessageMapping("/hello")
    @SendTo("/endpoint/greetings")
    public String greetings() throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("Connected to Greetings");
        return "hello";
    }

    @MessageMapping("playlists/{token}/{playlist}")
    public void createdPlaylist(@DestinationVariable String token, @DestinationVariable String playlist) throws Exception {
        String username = jwtUtil.extractUsername(token);
        Customer user = customerService.loadUserByUsername(username);
        ResponseModel response = new ResponseModel(user.getUsername(), playlist);
        this.sendingOperations.convertAndSend("/endpoint/playlist", playlistCreation(response));
    }

    @RequestMapping("/endpoint/playlist")
    @ResponseBody
    public String playlistCreation(ResponseModel response) throws Exception {
        return MessageFormat.format("User {0} created {1}!", response.getUsername(), response.getPlaylistName());
    }
}
