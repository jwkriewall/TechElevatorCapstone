package com.techelevator;

public class HomeworkAssignment {
		
		private int earnedMarks;
		private int possibleMarks;
		private String submitterName;
		private String letterGrade;
		
		public HomeworkAssignment(int possibleMarks, String submitterName) {
			
		}

		public int getEarnedMarks() {
			return earnedMarks;
		}

		public void setEarnedMarks(int earnedMarks) {
			this.earnedMarks = earnedMarks;
		}

		public int getPossibleMarks() {
			return possibleMarks;
		}

		public String getSubmitterName() {
			return submitterName;
		}

		public String getLetterGrade() {
			if (this.possibleMarks / this.earnedMarks >= 90.00) {
				letterGrade = "A";
			}
			if (this.possibleMarks / this.earnedMarks >= 80.00 && 
					this.possibleMarks / this.earnedMarks < 90.00) {
				letterGrade = "B";
			}
			if (this.possibleMarks / this.earnedMarks >= 70.00 && 
				this.possibleMarks / this.earnedMarks < 80.00){
				letterGrade = "C";
			}
			if (this.possibleMarks / this.earnedMarks >= 60.00 && 
					this.possibleMarks / this.earnedMarks < 70.00) {
				letterGrade = "D";
			}
			else {
				letterGrade = "F";
			}
			return letterGrade;
		}
		
		
	
}
