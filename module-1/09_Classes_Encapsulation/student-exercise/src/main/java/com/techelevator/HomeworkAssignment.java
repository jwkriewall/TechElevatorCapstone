package com.techelevator;

public class HomeworkAssignment {
		
		private int earnedMarks;
		private int possibleMarks;
		private String submitterName;
		private String letterGrade;
		
		public HomeworkAssignment(int possibleMarks, String submitterName) {
			this.possibleMarks = possibleMarks;
			this.submitterName = submitterName;
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
			
			double score = (((double) this.earnedMarks / this.possibleMarks) * 100.0);		
			
			if (score >= 90.00) {
				letterGrade = "A";
			}
			if (score >= 80.00 && score < 90.00) {
				letterGrade = "B";
			}
			if (score >= 70.00 && score < 80.00){
				letterGrade = "C";
			}
			if (score >= 60.00 && score < 70.00) {
				letterGrade = "D";
			}
			if (score < 60.00) {
				letterGrade = "F";
			}
		return letterGrade;
		
	}
}
