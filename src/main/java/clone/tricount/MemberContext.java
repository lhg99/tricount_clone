package clone.tricount;


import clone.tricount.model.Member;

public class MemberContext {

    private static final ThreadLocal<Member> memberThreadLocal = new ThreadLocal<>();

    public static void setMember(Member member) {
        memberThreadLocal.set(member);
    }

    public static Member getMember() {
        return memberThreadLocal.get();
    }
}
