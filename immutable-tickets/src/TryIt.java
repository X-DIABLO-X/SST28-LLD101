import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket originalTicket = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + originalTicket);
        IncidentTicket assignedTicket = service.assign(originalTicket, "agent@example.com");
        IncidentTicket escalatedTicket = service.escalateToCritical(assignedTicket);
        
        System.out.println("\n--- Proving State Updates ---");
        System.out.println("Original Ticket (unchanged) : " + originalTicket);
        System.out.println("Assigned Ticket             : " + assignedTicket);
        System.out.println("Escalated Ticket            : " + escalatedTicket);

        System.out.println("\n--- Proving Immutability ---");
        List<String> tags = originalTicket.getTags();
        try {
            System.out.println("Attempting to add a tag externally...");
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("Successfully blocked external tag mutation! Caught: " + e.getClass().getSimpleName());
        }
        
        System.out.println("\nFinal original ticket state: " + originalTicket);
    }
}
