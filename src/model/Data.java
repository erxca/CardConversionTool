package model;

public class Data {
	// 現在未使用のオブジェクト 一応残しておく

	String mailAddress; // メールアドレス
	String campanyName; // 会社名
	String lastName; // 姓（名まで入る？）
	String firstName; // 名（たぶん基本的にNULL）
	String phoneNumber; // 携帯電話番号

	public Data(String mailAddress, String campanyName, String lastName, String firstName, String phoneNumber) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.mailAddress = mailAddress;
		this.campanyName = campanyName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
	}

	public void showData() {
		System.out.println("\n----------\n");
		System.out.println("メール\t" + this.mailAddress);
		System.out.println("会社名\t" + this.campanyName);
		System.out.println("姓\t" + this.lastName);
		System.out.println("名\t" + this.firstName);
		System.out.println("携帯\t" + this.phoneNumber);
	}
}
