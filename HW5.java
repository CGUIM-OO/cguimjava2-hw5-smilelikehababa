
public class HW5 {
	public static void main(String[] args) {
		Table tbl=new Table(4);
		tbl.set_player(0, new Player("Player 1",300));
		tbl.set_player(1, new Player("Player 2",300));
		tbl.set_player(2, new Player("Player 3",300));
		tbl.set_player(3, new Player("Player 4",300));
		tbl.set_dealer(new Dealer());
		
		tbl.play();
	}
}
