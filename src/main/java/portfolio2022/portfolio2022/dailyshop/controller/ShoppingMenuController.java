package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class ShoppingMenuController {

    @GetMapping("/Men/Acc")
    public String MenAcc(){
        return "dailyshop/shopping-menu/men/men-acc";
    }
    @GetMapping("/Men/Bag")
    public String MenBag(){
        return "dailyshop/shopping-menu/men/men-bag";
    }
    @GetMapping("/Men/Casual")
    public String MenCasual(){
        return "dailyshop/shopping-menu/men/men-casual";
    }
    @GetMapping("/Men/Formal")
    public String MenFormal(){
        return "dailyshop/shopping-menu/men/men-formal";
    }
    @GetMapping("/Men/Outer")
    public String MenOuter(){
        return "dailyshop/shopping-menu/men/men-outer";
    }
    @GetMapping("/Men/Pants")
    public String MenPants(){
        return "dailyshop/shopping-menu/men/men-pants";
    }
    @GetMapping("/Men/Shirts")
    public String MenShirts(){
        return "dailyshop/shopping-menu/men/men-shirts";
    }
    @GetMapping("/Men/Shoes")
    public String MenShoes(){
        return "dailyshop/shopping-menu/men/men-shoes";
    }
    @GetMapping("/Men/Sports")
    public String MenSports(){
        return "dailyshop/shopping-menu/men/men-sports";
    }
    @GetMapping("/Men/Wallet")
    public String MenWallet(){
        return "dailyshop/shopping-menu/men/men-wallet";
    }
    @GetMapping("/Women/Acc")
    public String WomenAcc(){
        return "dailyshop/shopping-menu/women/women-acc";
    }
    @GetMapping("/Women/Bag")
    public String WomenBag(){
        return "dailyshop/shopping-menu/women/women-bag";
    }
    @GetMapping("/Women/Casual")
    public String WomenCasual(){
        return "dailyshop/shopping-menu/women/women-casual";
    }
    @GetMapping("/Women/Formal")
    public String WomenFormal(){
        return "dailyshop/shopping-menu/women/women-formal";
    }
    @GetMapping("/Women/Outer")
    public String WomenOuter(){
        return "dailyshop/shopping-menu/women/women-outer";
    }
    @GetMapping("/Women/Pants")
    public String WomenPants(){
        return "dailyshop/shopping-menu/women/women-pants";
    }
    @GetMapping("/Women/Shirts")
    public String WomenShirts(){
        return "dailyshop/shopping-menu/women/women-shirts";
    }
    @GetMapping("/Women/Shoes")
    public String WomenShoes(){
        return "dailyshop/shopping-menu/women/women-shoes";
    }
    @GetMapping("/Women/Sports")
    public String WomenSports(){
        return "dailyshop/shopping-menu/women/women-sports";
    }
    @GetMapping("/Women/Wallet")
    public String WomenWallet(){
        return "dailyshop/shopping-menu/women/women-wallet";
    }
    @GetMapping("/Kids/Acc")
    public String KidsAcc(){
        return "dailyshop/shopping-menu/kids/kids-acc";
    }
    @GetMapping("/Kids/Bag")
    public String KidsBag(){
        return "dailyshop/shopping-menu/kids/kids-bag";
    }
    @GetMapping("/Kids/Casual")
    public String KidsCasual(){
        return "dailyshop/shopping-menu/kids/kids-casual";
    }
    @GetMapping("/Kids/Formal")
    public String KidsFormal(){
        return "dailyshop/shopping-menu/kids/kids-formal";
    }
    @GetMapping("/Kids/Outer")
    public String KidsOuter(){
        return "dailyshop/shopping-menu/kids/kids-outer";
    }
    @GetMapping("/Kids/Pants")
    public String KidsPants(){
        return "dailyshop/shopping-menu/kids/kids-pants";
    }
    @GetMapping("/Kids/Shirts")
    public String KidsShirts(){
        return "dailyshop/shopping-menu/kids/kids-shirts";
    }
    @GetMapping("/Kids/Shoes")
    public String KidsShoes(){
        return "dailyshop/shopping-menu/kids/kids-shoes";
    }
    @GetMapping("/Kids/Sports")
    public String KidsSports(){
        return "dailyshop/shopping-menu/kids/kids-sports";
    }
    @GetMapping("/Kids/Wallet")
    public String KidsWallet(){
        return "dailyshop/shopping-menu/kids/kids-wallet";
    }
    @GetMapping("/Sports")
    public String Sports(){
        return "dailyshop/shopping-menu/sports/sports";
    }
    @GetMapping("/Digital/Acc")
    public String DigitalAcc(){
        return "dailyshop/shopping-menu/digital/digital-acc";
    }
    @GetMapping("/Digital/Camera")
    public String DigitalCamerat(){
        return "dailyshop/shopping-menu/digital/digital-camera";
    }
    @GetMapping("/Digital/Laptop")
    public String DigitalLaptop(){
        return "dailyshop/shopping-menu/digital/digital-laptop";
    }
    @GetMapping("/Digital/Mobile")
    public String DigitalMobile(){
        return "dailyshop/shopping-menu/digital/digital-mobile";
    }
    @GetMapping("/Digital/Tablet")
    public String DigitalTablet(){
        return "dailyshop/shopping-menu/digital/digital-tablet";
    }
    @GetMapping("/Furniture")
    public String Furniture(){
        return "dailyshop/shopping-menu/furniture/furniture";
    }
}
