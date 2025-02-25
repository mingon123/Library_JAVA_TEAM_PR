package com.library.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.library.DAO.NoticeDAO;
import com.library.DAO.impl.NoticeDAOImpl;
import com.library.DTO.Notice;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.NoticeService;
import com.library.service.Impl.BookServiceImpl;
import com.library.service.Impl.MemberServiceImpl;
import com.library.service.Impl.NoticeServiceImpl;

public class MainMenu {
	private BufferedReader br;
    private BookService bookService;
    private NoticeService noticeService;
    private MemberService memberService;
	
	public MainMenu() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));			
			this.bookService = new BookServiceImpl();
			this.memberService = new MemberServiceImpl();
			
	        NoticeDAO noticeDAO = new NoticeDAOImpl();
	        this.noticeService = new NoticeServiceImpl(noticeDAO, br);
			callMenu();
        } catch (IOException e) {
            System.out.println("입력 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 오류 발생: " + e.getMessage());
        }
	}
	
    public void callMenu() throws NumberFormatException, IOException {
        while (true) {
            System.out.println("===== Main Menu =====");
            for (MainMenuEnum menu : MainMenuEnum.values()) {
                System.out.println(menu.getNumber() + ". " + menu.getTitle());
            }
            System.out.print("메뉴를 선택하세요: ");
            int choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1: // 도서 목록
                    System.out.println("도서 목록 메뉴로 이동");
                    break;
                case 2: // 도서 검색
                    System.out.println("도서 검색 메뉴로 이동");
                    
                    break;
                case 3: // 공지사항 확인
                	System.out.println("공지사항 메뉴로 이동");
                	noticeService.showNotices();
                    break;
                case 4: // 회원가입
					System.out.println("회원가입 페이지입니다.");
					memberService.signUpProcess();
                    break;
                case 5: // 로그인
                    memberService.login();
                    break;
                case 6: // 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
                    break;
            }
        }
    }
}