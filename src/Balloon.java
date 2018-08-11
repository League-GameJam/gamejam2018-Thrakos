import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Balloon {

	int speed;

	int lane;
	int prevLane;
	int laneChange;
	boolean movingLanes;

	boolean isAlive;

	int x;
	int y;
	int width;
	int height;

	int upDown;
	boolean down;

	Rectangle collisionBox;

	Balloon(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		upDown = 0;
		down = false;

		lane = 1;
		prevLane = 1;

		collisionBox = new Rectangle(x, y, width, height);
		
		isAlive = true;

	}

	void update() {

		if (prevLane != lane) {
			movingLanes = true;
		}

		if (!movingLanes) {
			if (upDown < 450 && !down) {
				if (upDown % 30 == 0) {
					y++;
				}
				upDown++;
				if (upDown == 450) {
					down = true;
				}
			} else if (upDown > 0 && down) {
				if (upDown % 30 == 0) {
					y--;
				}
				upDown--;
				if (upDown == 0) {
					down = false;
				}
			}
		} else {
			if (prevLane > lane) {
				if (laneChange < 350) {
					if (laneChange % 2 == 0) {
						y++;
					}
					laneChange++;
				} else {
					movingLanes = false;
					laneChange = 0;
				}
			} else if (prevLane < lane) {
				if (laneChange > -350) {
					if (laneChange % 2 == 0) {
						y--;
					}
					laneChange--;
				} else {
					movingLanes = false;
					laneChange = 0;
				}
			}
		}

		collisionBox.setBounds(x, y, width, height);

		if (!movingLanes) {
			prevLane = lane;
		}

	}

	void draw(Graphics g) {

		if (isAlive) {
			g.setColor(Color.WHITE);
			g.fillOval(x, y, width, height);
			g.drawArc((x + (width / 2)) - 5, y + height, 10, 40, 90, 180);
			g.drawArc((x + (width / 2)) - 5, y + (height * 2) - 8, 10, 40, 90, -180);
		}

	}

}
