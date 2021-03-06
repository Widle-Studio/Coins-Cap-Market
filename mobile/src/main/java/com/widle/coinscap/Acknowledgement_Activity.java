package com.widle.coinscap;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Acknowledgement_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;

    private TextView tv_title, tv_content;

//    private WebView webview;

    private String typee = "";

    private static final String APP_ID = "ca-app-pub-7122988079171533~5155426249";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgement);

        typee = getIntent().getStringExtra("type");

        init();

    }

    public void init() {

        iv_back = (ImageView) findViewById(R.id.iv_back);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);

        Typeface type = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-BoldUpright.otf");
        Typeface type1 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-SemiboldUpright.otf");
        Typeface type2 = Typeface.createFromAsset(getAssets(), "Fonts/Titillium-LightUpright.otf");

        tv_title.setTypeface(type2);
        tv_content.setTypeface(type1);

        if (typee.equals("1")) {
            tv_title.setText("About");
            tv_content.setText("Coins Cap Market is cryptocurrency source with price information and news. We supply fast and reliable price information of top cryptocurrency.");
        } else if (typee.equals("2")) {
            Intent intent = new Intent(Acknowledgement_Activity.this, Help_Activity.class);
            startActivity(intent);
        } else if (typee.equals("3")) {
            tv_title.setText("Acknowledgement");

            String volly = "Volly\n\n  \"License\" shall mean the terms and conditions for use, reproduction,\n" +
                    "      and distribution as defined by Sections 1 through 9 of this document.\n" +
                    "\n" +
                    "      \"Licensor\" shall mean the copyright owner or entity authorized by\n" +
                    "      the copyright owner that is granting the License.\n" +
                    "\n" +
                    "      \"Legal Entity\" shall mean the union of the acting entity and all\n" +
                    "      other entities that control, are controlled by, or are under common\n" +
                    "      control with that entity. For the purposes of this definition,\n" +
                    "      \"control\" means (i) the power, direct or indirect, to cause the\n" +
                    "      direction or management of such entity, whether by contract or\n" +
                    "      otherwise, or (ii) ownership of fifty percent (50%) or more of the\n" +
                    "      outstanding shares, or (iii) beneficial ownership of such entity.\n" +
                    "\n" +
                    "      \"You\" (or \"Your\") shall mean an individual or Legal Entity\n" +
                    "      exercising permissions granted by this License.\n" +
                    "\n" +
                    "      \"Source\" form shall mean the preferred form for making modifications,\n" +
                    "      including but not limited to software source code, documentation\n" +
                    "      source, and configuration files.\n" +
                    "\n" +
                    "      \"Object\" form shall mean any form resulting from mechanical\n" +
                    "      transformation or translation of a Source form, including but\n" +
                    "      not limited to compiled object code, generated documentation,\n" +
                    "      and conversions to other media types.\n" +
                    "\n" +
                    "      \"Work\" shall mean the work of authorship, whether in Source or\n" +
                    "      Object form, made available under the License, as indicated by a\n" +
                    "      copyright notice that is included in or attached to the work\n" +
                    "      (an example is provided in the Appendix below).\n" +
                    "\n" +
                    "      \"Derivative Works\" shall mean any work, whether in Source or Object\n" +
                    "      form, that is based on (or derived from) the Work and for which the\n" +
                    "      editorial revisions, annotations, elaborations, or other modifications\n" +
                    "      represent, as a whole, an original work of authorship. For the purposes\n" +
                    "      of this License, Derivative Works shall not include works that remain\n" +
                    "      separable from, or merely link (or bind by name) to the interfaces of,\n" +
                    "      the Work and Derivative Works thereof.\n" +
                    "\n" +
                    "      \"Contribution\" shall mean any work of authorship, including\n" +
                    "      the original version of the Work and any modifications or additions\n" +
                    "      to that Work or Derivative Works thereof, that is intentionally\n" +
                    "      submitted to Licensor for inclusion in the Work by the copyright owner\n" +
                    "      or by an individual or Legal Entity authorized to submit on behalf of\n" +
                    "      the copyright owner. For the purposes of this definition, \"submitted\"\n" +
                    "      means any form of electronic, verbal, or written communication sent\n" +
                    "      to the Licensor or its representatives, including but not limited to\n" +
                    "      communication on electronic mailing lists, source code control systems,\n" +
                    "      and issue tracking systems that are managed by, or on behalf of, the\n" +
                    "      Licensor for the purpose of discussing and improving the Work, but\n" +
                    "      excluding communication that is conspicuously marked or otherwise\n" +
                    "      designated in writing by the copyright owner as \"Not a Contribution.\"\n" +
                    "\n" +
                    "      \"Contributor\" shall mean Licensor and any individual or Legal Entity\n" +
                    "      on behalf of whom a Contribution has been received by Licensor and\n" +
                    "      subsequently incorporated within the Work.\n" +
                    "\n" +
                    "   2. Grant of Copyright License. Subject to the terms and conditions of\n" +
                    "      this License, each Contributor hereby grants to You a perpetual,\n" +
                    "      worldwide, non-exclusive, no-charge, royalty-free, irrevocable\n" +
                    "      copyright license to reproduce, prepare Derivative Works of,\n" +
                    "      publicly display, publicly perform, sublicense, and distribute the\n" +
                    "      Work and such Derivative Works in Source or Object form.\n" +
                    "\n" +
                    "   3. Grant of Patent License. Subject to the terms and conditions of\n" +
                    "      this License, each Contributor hereby grants to You a perpetual,\n" +
                    "      worldwide, non-exclusive, no-charge, royalty-free, irrevocable\n" +
                    "      (except as stated in this section) patent license to make, have made,\n" +
                    "      use, offer to sell, sell, import, and otherwise transfer the Work,\n" +
                    "      where such license applies only to those patent claims licensable\n" +
                    "      by such Contributor that are necessarily infringed by their\n" +
                    "      Contribution(s) alone or by combination of their Contribution(s)\n" +
                    "      with the Work to which such Contribution(s) was submitted. If You\n" +
                    "      institute patent litigation against any entity (including a\n" +
                    "      cross-claim or counterclaim in a lawsuit) alleging that the Work\n" +
                    "      or a Contribution incorporated within the Work constitutes direct\n" +
                    "      or contributory patent infringement, then any patent licenses\n" +
                    "      granted to You under this License for that Work shall terminate\n" +
                    "      as of the date such litigation is filed.\n" +
                    "\n" +
                    "   4. Redistribution. You may reproduce and distribute copies of the\n" +
                    "      Work or Derivative Works thereof in any medium, with or without\n" +
                    "      modifications, and in Source or Object form, provided that You\n" +
                    "      meet the following conditions:\n" +
                    "\n" +
                    "      (a) You must give any other recipients of the Work or\n" +
                    "          Derivative Works a copy of this License; and\n" +
                    "\n" +
                    "      (b) You must cause any modified files to carry prominent notices\n" +
                    "          stating that You changed the files; and\n" +
                    "\n" +
                    "      (c) You must retain, in the Source form of any Derivative Works\n" +
                    "          that You distribute, all copyright, patent, trademark, and\n" +
                    "          attribution notices from the Source form of the Work,\n" +
                    "          excluding those notices that do not pertain to any part of\n" +
                    "          the Derivative Works; and\n" +
                    "\n" +
                    "      (d) If the Work includes a \"NOTICE\" text file as part of its\n" +
                    "          distribution, then any Derivative Works that You distribute must\n" +
                    "          include a readable copy of the attribution notices contained\n" +
                    "          within such NOTICE file, excluding those notices that do not\n" +
                    "          pertain to any part of the Derivative Works, in at least one\n" +
                    "          of the following places: within a NOTICE text file distributed\n" +
                    "          as part of the Derivative Works; within the Source form or\n" +
                    "          documentation, if provided along with the Derivative Works; or,\n" +
                    "          within a display generated by the Derivative Works, if and\n" +
                    "          wherever such third-party notices normally appear. The contents\n" +
                    "          of the NOTICE file are for informational purposes only and\n" +
                    "          do not modify the License. You may add Your own attribution\n" +
                    "          notices within Derivative Works that You distribute, alongside\n" +
                    "          or as an addendum to the NOTICE text from the Work, provided\n" +
                    "          that such additional attribution notices cannot be construed\n" +
                    "          as modifying the License.\n" +
                    "\n" +
                    "      You may add Your own copyright statement to Your modifications and\n" +
                    "      may provide additional or different license terms and conditions\n" +
                    "      for use, reproduction, or distribution of Your modifications, or\n" +
                    "      for any such Derivative Works as a whole, provided Your use,\n" +
                    "      reproduction, and distribution of the Work otherwise complies with\n" +
                    "      the conditions stated in this License.\n" +
                    "\n" +
                    "   5. Submission of Contributions. Unless You explicitly state otherwise,\n" +
                    "      any Contribution intentionally submitted for inclusion in the Work\n" +
                    "      by You to the Licensor shall be under the terms and conditions of\n" +
                    "      this License, without any additional terms or conditions.\n" +
                    "      Notwithstanding the above, nothing herein shall supersede or modify\n" +
                    "      the terms of any separate license agreement you may have executed\n" +
                    "      with Licensor regarding such Contributions.\n" +
                    "\n" +
                    "   6. Trademarks. This License does not grant permission to use the trade\n" +
                    "      names, trademarks, service marks, or product names of the Licensor,\n" +
                    "      except as required for reasonable and customary use in describing the\n" +
                    "      origin of the Work and reproducing the content of the NOTICE file.\n" +
                    "\n" +
                    "   7. Disclaimer of Warranty. Unless required by applicable law or\n" +
                    "      agreed to in writing, Licensor provides the Work (and each\n" +
                    "      Contributor provides its Contributions) on an \"AS IS\" BASIS,\n" +
                    "      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or\n" +
                    "      implied, including, without limitation, any warranties or conditions\n" +
                    "      of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A\n" +
                    "      PARTICULAR PURPOSE. You are solely responsible for determining the\n" +
                    "      appropriateness of using or redistributing the Work and assume any\n" +
                    "      risks associated with Your exercise of permissions under this License.\n" +
                    "\n" +
                    "   8. Limitation of Liability. In no event and under no legal theory,\n" +
                    "      whether in tort (including negligence), contract, or otherwise,\n" +
                    "      unless required by applicable law (such as deliberate and grossly\n" +
                    "      negligent acts) or agreed to in writing, shall any Contributor be\n" +
                    "      liable to You for damages, including any direct, indirect, special,\n" +
                    "      incidental, or consequential damages of any character arising as a\n" +
                    "      result of this License or out of the use or inability to use the\n" +
                    "      Work (including but not limited to damages for loss of goodwill,\n" +
                    "      work stoppage, computer failure or malfunction, or any and all\n" +
                    "      other commercial damages or losses), even if such Contributor\n" +
                    "      has been advised of the possibility of such damages.\n" +
                    "\n" +
                    "   9. Accepting Warranty or Additional Liability. While redistributing\n" +
                    "      the Work or Derivative Works thereof, You may choose to offer,\n" +
                    "      and charge a fee for, acceptance of support, warranty, indemnity,\n" +
                    "      or other liability obligations and/or rights consistent with this\n" +
                    "      License. However, in accepting such obligations, You may act only\n" +
                    "      on Your own behalf and on Your sole responsibility, not on behalf\n" +
                    "      of any other Contributor, and only if You agree to indemnify,\n" +
                    "      defend, and hold each Contributor harmless for any liability\n" +
                    "      incurred by, or claims asserted against, such Contributor by reason\n" +
                    "      of your accepting any such warranty or additional liability.\n" +
                    "\n" +
                    "   END OF TERMS AND CONDITIONS\n" +
                    "\n" +
                    "   APPENDIX: How to apply the Apache License to your work.\n" +
                    "\n" +
                    "      To apply the Apache License to your work, attach the following\n" +
                    "      boilerplate notice, with the fields enclosed by brackets \"[]\"\n" +
                    "      replaced with your own identifying information. (Don't include\n" +
                    "      the brackets!)  The text should be enclosed in the appropriate\n" +
                    "      comment syntax for the file format. We also recommend that a\n" +
                    "      file or class name and description of purpose be included on the\n" +
                    "      same \"printed page\" as the copyright notice for easier\n" +
                    "      identification within third-party archives.\n" +
                    "\n" +
                    "   Copyright [yyyy] [name of copyright owner]\n" +
                    "\n" +
                    "   Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                    "   you may not use this file except in compliance with the License.\n" +
                    "   You may obtain a copy of the License at\n" +
                    "\n" +
                    "       http://www.apache.org/licenses/LICENSE-2.0\n" +
                    "\n" +
                    "   Unless required by applicable law or agreed to in writing, software\n" +
                    "   distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                    "   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                    "   See the License for the specific language governing permissions and\n" +
                    "   limitations under the License.";

//            String acknow1 = "com.android.volley:volley:1.0.0 \n";
//            String acknow2 = "de.hdodenhof:circleimageview:2.1.0\n";
//            String acknow3 = "com.yarolegovich:mp:1.0.9\n";
//            String acknow4 = "com.google.android.exoplayer:exoplayer:r2.5.2\n";
//            String acknow5 = "com.github.PhilJay:MPAndroidChart:v2.2.4\n";
//            String acknow6 = "com.google.code.gson:gson:2.2.+\n";
//            String acknow7 = "com.github.bumptech.glide:glide:4.3.1 \n";
            tv_content.setText(volly);
        } else if (typee.equals("4")) {
            tv_title.setText("Privacy");
            tv_content.setText("Coins Cap Market are committed to protecting and respecting your privacy. \n\n - SCOPE OF POLICY 1.1 This policy together with the General Terms of Service and our Business Terms of Service and our Business Terms (as applicable) apply to your use of: a) our website at www.coinscapmarket.com (the \" Site\") including, without limitation, the coinscapmarket Dashboard available to coinscapmarket Dashboard Users pursuant to the Business Terms; b) the coinscapmarket Mobile App ( App ) once you have downloaded a copy of the App onto your mobile telephone or handheld device ( Device ); and c) any of the services accessible through the App or the Site (the Services ). 1.2 This policy sets out the basis on which any personal data we collect from you, or that you provide to us, will be processed by us. Please read the following carefully to understand our views and practices regarding your personal data and how we will treat it. 1.3 For the purpose of the Data Protection Act 1998, the data controller is coinscapmarket. \n\n - \n" +
                    "INFORMATION WE COLLECT FROM YOU 2.1 We will collect and process the following data about you: a) Information you give us (Submitted information): This is information you give us about you by filling in forms on the App and/or the Site, or by corresponding with us (for example, by e-mail or via the chat functions on the App and/or the Site). It includes information you provide when you register to use the App, download or register the App, subscribe to any of our services, enter into any transaction on the App or the Site (such as a Top Up, Instant Transfer, coinscapmarket Bank Transfer, Electronic Money Exchange), participate in discussion boards or other social media functions on the App or the Site, enter a competition, promotion or survey and when you report a problem with an App, the Services, or the Site. If you contact us, we will keep a record of that correspondence. The information you give us may include your name, address, date of birth, e-mail address, phone number, the Device's phone number, username, password and other registration information, financial, details of your bank account including the bank account number, bank sort code, IBAN, details of your debit and credit cards including the long number, relevant expiry dates and CVC, identification document numbers, copies of identification documents (for example, passport, driving licence and utility bill) personal description and photograph and any other information you provide us in order to prove your eligibility to use our services. b) Information we collect about you and your device. Each time you visit the App or our Site we will automatically collect the following information: (i) technical information, including the internet protocol (IP) address used to connect your computer to the Internet, your login information, browser type and version, time zone setting, browser plug-in types and versions, operating system and platform, device information and the type of mobile device you use, a unique device identifier (for example, your Device's IMEI number, the MAC address of the Device's wireless network interface, or the mobile phone number used by the Device), mobile network information, your mobile operating system, the type of mobile browser you use, time zone setting (Device Information); (ii) information about your visit, including the full uniform resource locators (URL), clickstream to, through and from our site (including date and time), services you viewed or searched for, page response times, download errors, length of visits to certain pages, page interaction information (such as scrolling, clicks, and mouse-overs), methods used to browse away from the page, device information and any phone number used to call our customer service number; (iii) transaction information including date, time, amount, currencies used, exchange rate, beneficiary details, details and location of the merchant or ATMs associated with the transaction, IP address of sender and receiver, sender's and receiver's name and registration information, messages sent or received with the payment, device information used to facilitate the payment and the payment instrument used. (iv) information stored on your Device, including if you allow coinscapmarket access contact information from your address book, login information, photos, videos or other digital content, check ins (Content Information). The App will periodically recollect this information in order to stay up-to-date; (v) details of your use of our App or your visits to our Site including transaction details relating to your use of our services, including who you have sent money or electronic money to, foreign exchange transactions you have entered into, the time, date and location of the place the transaction was entered into. c) Location information. We use GPS technology and your IP address to determine your location – this may be used when the App is running in the foreground and the background of your Device. This is used to prevent fraud, for instance if your mobile phone is saying that you are based in the India but your card is being used to enter into an ATM Withdrawal or point of sale purchase in Spain, we may not allow that transaction to be processed. Our card protection and fraud-prevention measures require this personal data for the feature to work. If you wish to use the particular feature, you will be asked to consent to your data being used for this purpose. You can withdraw your consent at any time by de-selecting \"location based security\" on the Card Security part of the App d) Information we receive from other sources (Third Party Information). We are working closely with third parties (including, for example, business partners, sub-contractors in technical, payment and delivery services, advertising networks, analytics providers, search information providers, credit reference agencies, fraud prevention agencies, customer service providers, developers, social media organisations such as Facebook, Twitter and/or Google). The use of credit reference agencies and fraud prevention agencies is not limited to such agencies based in the United Kingdom and includes such agencies overseas. Any such search under this section may leave a footprint on your credit file. By agreeing to the terms of this privacy policy, you agree that we may carry out such credit search in the knowledge that it may leave a footprint on your credit history. We do not allow joint account holders, however in certain circumstances credit reference agencies may link your record with your spouse, partner or other financial associate. You are entitled to access your personal records held by credit and fraud prevention agencies. If you would like details of the credit reference and fraud prevention agencies from which we have obtained or may obtain information about you, please contact us at compliance@coinscapmarket.com. If you allow us to, we will collect friends lists from Facebook and similar information from other third parties such as Twitter and Google – the App will periodically re-collect this information in order to stay up-to-date. We will also receive information about you from third parties we use to verify your identity. \n\n - COOKIES 3.1 We use cookies to distinguish you from other users of the App or the Site. This helps us to provide you with a good experience when you use the App or browse our Site and also allows us to improve the App and our Site. For detailed information on the cookies we use and the purposes for which we use them. \n\n - USES MADE OF THE INFORMATION 4.1 We use information held about you in the following ways: (a) Submitted Information: We will use this information: to carry out our obligations arising from any transactions you enter into with us, for example Top Ups, Instant Transfers, coinscapmarket Bank Transfers, Electronic Money Exchanges, ATM Withdrawals and coinscapmarket Card Purchases and to provide you with the information, products and services that you request from us; to provide you with information about other goods and services we offer that are similar to those that you have already purchased or enquired about; to provide you, or permit selected third parties to provide you, with information about goods or services we feel may interest you. to verify your identity to protect against fraud, comply with financial crime laws and to confirm your eligibility to use our products and services; to notify you about changes to our service; to facilitate social interactions through our services and to make you aware if any of your contacts who are coinscapmarket Users and have location services enabled, are in the same area as you; and to ensure that content from our site is presented in the most effective manner for you and for your computer. (b) Device Information: We will use this information: to administer our Site and the App for internal operations, including troubleshooting, data analysis, testing, research, statistical and survey purposes; to improve our Site and the App to ensure that content is presented in the most effective manner for you and for your computer; to allow you to participate in interactive features of our service, when you choose to do so; as part of our efforts to keep our Site and the App safe and secure; to measure or understand the effectiveness of advertising we serve to you and others, and to deliver relevant advertising to you; to make suggestions and recommendations to you and other users of our Site and the App about goods or services that may interest you or them; to verify your identity, protect against fraud, comply with anti-financial crime laws and to confirm your eligibility to use our products and services; and, to comply with our regulatory obligations. c) Location Information: We will use this information: to deliver relevant advertising to you, for example, information on nearby merchants; to protect against fraud; and to make you aware if any of your contacts, who are coinscapmarket Users and have location services enabled, are in the same area as you. d) Third Party Information: We will combine this information with information you give to us and information we collect about you. We will use this information and the combined information: to help us better understand your financial circumstances and behaviour so that we may make decisions about how we manage your coinscapmarket Account; to process applications for products and services available through us including making decisions about whether to agree to approve any applications; for the purposes set out above (depending on the types of information we receive). 4.2 We may associate any category of information with any other category of information and will treat the combined information as personal data in accordance with this policy for as long as it is combined. 4.3 We do not disclose information about identifiable individuals to our advertisers, but we may provide them with anonymous aggregate information about our users (for example, we may inform them that 500 men aged under 30 have clicked on their advertisement on any given day). We may also use such aggregate information to help advertisers reach the kind of audience they want to target (for example, women in SW1). We will use of the personal data we have collected from you to enable us to comply with our advertisers' wishes by displaying their advertisement to that target audience. \n\n - DISCLOSURE OF YOUR INFORMATION 5.1 We will disclose the data we collect from you to the following third parties: a) business partners (including banking partners and banking intermediaries), suppliers and sub-contractors for the performance of any contract we enter into with them or you; b) advertisers and advertising networks that require the data to select and serve relevant adverts to you and others. We do not disclose information about identifiable individuals to our advertisers, but we will provide them with aggregate information about our users (for example, we may inform them that 500 men aged under 30 have clicked on their advertisement on any given day). We may also use such aggregate information to help advertisers reach the kind of audience they want to target (for example, women in SW1). We may make use of the personal data we have collected from you to enable us to comply with our advertisers' wishes by displaying their advertisement to that target audience; c) analytics and search engine providers that assist us in the improvement and optimisation of our site; d) third parties to verify your identity, protect against fraud, comply with anti-money laundering laws and to confirm your eligibility to use our products and services; and, e) credit reference agencies for the purpose of assessing your credit score where this is a condition of us entering into a contract with you. 5.2 You agree that we have the right to disclose your personal information to any member of our group, which means our subsidiaries, our ultimate holding company and its subsidiaries, as defined in section 1159 of the Companies Act 2006. 5.3 We will disclose your personal information to third parties: a) In the event that we sell or buy any business or assets, in which case we will disclose your personal data to the prospective seller or buyer of such business or assets. b) If coinscapmarket Limited or substantially all of its assets are acquired by a third party, in which case personal data held by it about its customers will be one of the transferred assets. c) If we are under a duty to disclose or share your personal data in order to comply with any legal or regulatory obligation or request. d) In order to: (i) enforce or apply the Standard Terms and/or the Business Terms and/or any other agreements between you and us or to investigate potential breaches; or (ii) protect the rights, property or safety of coinscapmarket Limited, our customers or others. This includes exchanging information with other companies and organisations for the purposes of fraud protection and credit risk reduction. \n\n - WHERE WE STORE YOUR PERSONAL DATA 6.1 The data that we collect from you will be transferred to, and stored at, a destination outside the European Economic Area ( EEA ). It will also be processed by staff operating outside the EEA who work for us or for one of our suppliers. These staff may be engaged in the fulfilment of your request, order or reservation, the processing of your payment details and the provision of support services. By submitting your personal data, you agree to this transfer, storing or processing. We will take all steps reasonably necessary to ensure that your data is treated securely and in accordance with this privacy policy. 6.2 All information you provide to us is stored on our secure servers. Any payment transactions carried out by us or our chosen third-party provider of payment processing services will be encrypted using Secured Sockets Layer technology or a secure virtual private network. Where we have given you (or where you have chosen) a password that enables you to access certain parts of our App and/or our Site, you are responsible for keeping this password confidential. We ask you not to share a password with anyone. 6.3 Unfortunately, the transmission of information via the internet is not completely secure. Although we will do our best to protect your personal data, we cannot guarantee the security of your data transmitted to our App or our Site; any transmission is at your own risk. Once we have received your information, we will use strict procedures and security features to try to prevent unauthorised access. 6.4 Certain Services include social networking, chat room or forum features. Ensure when using these features that you do not submit any personal data that you do not want to be seen, collected or used by other users. \n\n - YOUR RIGHTS 7.1 You have the right to ask us not to process your personal data for marketing purposes. We will usually inform you (before collecting your data) if we intend to use your data for such purposes or if we intend to disclose your information to any third party for such purposes. You can exercise your right to prevent such processing by checking certain boxes on the forms we use to collect your data. You can also exercise the right at any time by contacting us using the chat function on the App or the Site. 7.2 Our App and our Site may, from time to time, contain links to and from the websites of our partner networks, advertisers and affiliates (including, but not limited to, websites on which the App or the Services are advertised). If you follow a link to any of these websites, please note that these websites and any services that may be accessible through them have their own privacy policies and that we do not accept any responsibility or liability for these policies or for any personal data that may be collected through these websites or services, such as contact and location data. Please check these policies before you submit any personal data to these websites or use these services. \n\n - ACCESS TO INFORMATION 8.1 The Data Protection Act 1998 gives you the right to access information held about you. Your right of access can be exercised in accordance with that Act. Any access request will be subject to a fee of £10 to meet our costs in providing you with details of the information we hold about you. 8.2 You can access some of the information we hold about you on the App or the Site by visiting the relative page on the App or the Site. On these pages, you may edit the information we hold about you. For security reasons, you will not be able to change all the information we hold about you on the App and the Site. If you wish to change or delete information we hold about you which you cannot edit or delete from the App or the Site, please contact our customer support via the chat function on the App or the Site. \n\n - CHANGES TO PRIVACY POLICY 9.1 Any changes we may make to our privacy policy in the future will be posted on this page and, where appropriate, notified to you by SMS by e-mail and/or when you next start the App or log onto the Site. The new terms may be displayed on-screen and you may be required to read and accept them to continue your use of the App or the Services. \n\n - CONTACT 10.1 Questions, comments and requests regarding this privacy policy are welcomed and should be addressed to customer services, which can be contacted using the appropriate link on the App or the Site.");
        } else if (typee.equals("5")) {
//            webview.loadUrl("http://coinscapmarket.com/terms-of-service.html");
            tv_title.setText("Terms of Service");
            tv_content.setText(" - These Terms constitute a framework agreement which sets out the terms of (a) you and us entering into coinscapmarket Transaction(s); and (b) the use of the coinscapmarket Dashboard and other coinscapmarket Services. Before you can enter into coinscapmarket Transactions and benefit from the coinscapmarket Services with us you are required to: read these Terms and tick the box confirming the accuracy of the information provided and your agreement with these Terms; and provide us with such documentation, photographs and information as we may reasonably request to comply with our regulatory obligations. \n\n - These Terms incorporate the Website Terms, the Cookie Policy, the Privacy Policy, the Website Acceptable Use Policy, the Mobile App Terms and any Supplements by this reference. By accepting these Terms, you are deemed to have accepted the terms and conditions of our third party service providers including but not limited to, for the avoidance of doubt, the relevant coinscapmarket Cardholder Terms. \n\n - Once you have completed the above and you have passed our internal checks, we shall make the coinscapmarket Dashboard available to you. \n\n - You confirm that you have provided the correct Information during the process of creating a coinscapmarket Account. You undertake that, if your details change, you will notify us immediately. You shall bear any losses that occur due to the submission of invalid, incorrect or inaccurate Information.");
        } else if (typee.equals("6")) {
//            webview.loadUrl("http://coinscapmarket.com/cookie-policy.html");
            tv_title.setText("Cookie Policy");
            tv_content.setText(" - We use cookies to distinguish you from other users of the App or the Site. This helps us to provide you with a good experience when you use the App or browse our Site and also allows us to improve the App and our Site. For detailed information on the cookies we use and the purposes for which we use them.");
        } else if (typee.equals("7")) {
//            webview.getSettings().setJavaScriptEnabled(true);
//            webview.loadUrl("http://coinscapmarket.com/rate-us.html");
            tv_title.setText("Rate us");

        }

        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;
        }
    }


}
