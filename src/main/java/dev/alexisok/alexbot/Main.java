package dev.alexisok.alexbot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/***/
public final class Main extends ListenerAdapter {
    
    private static final HashMap<String, String> RESPONSES = new HashMap<>();
    
    private static final List<String> HAS_FIRST_RESPOND = new ArrayList<>();
    
    private static boolean hasAlexApproved = false;
    
    static {
        RESPONSES.putIfAbsent("121919449996460033", "wow, i didn't think veld reviewed bots!");
        RESPONSES.putIfAbsent("395526710101278721", "iara kind of quirky ngl.");
        RESPONSES.putIfAbsent("321714991050784770", "marco is pretty cool, hope i end up in his server.");
        RESPONSES.putIfAbsent("189759562910400512", "brain looked over my verification questions for discord and made a neat guide on it too");
        RESPONSES.putIfAbsent("125492204234997761", "don't know panku");
        RESPONSES.putIfAbsent("187316528100802560", "eri is pretty cool and their pfp has a cute fox or whatever it is");
        RESPONSES.putIfAbsent("310853886191599616", "kyoso is pretty cool, talked to him a bit in vc");
        RESPONSES.putIfAbsent("325087287539138560", "no idea who lyss is sry");
        RESPONSES.putIfAbsent("608767196839018507", "nom bot live-action adaptation");
        RESPONSES.putIfAbsent("172075838806818817", "is that spaghetti on your pfp");
        RESPONSES.putIfAbsent("253233185800847361", "this is a two part message, have the other bean reply");
        RESPONSES.putIfAbsent("344954369285947392", "to see the whole message");
        RESPONSES.putIfAbsent("141075183271280641", "idk safe");
        RESPONSES.putIfAbsent("254814547326533632", "ur username reminds me of the first jojo opening");
        RESPONSES.putIfAbsent("293870361001328644", "if oliy had a son it would probably be alex d");
        RESPONSES.putIfAbsent("311553339261321216", "\"What detailed info do you have of us lmfao\"");
        RESPONSES.putIfAbsent("247741991310327810", "shiv is a mod again pog");
        
        //briv its literalie me
        RESPONSES.putIfAbsent("541763812676861952", "mah owner!!!1!1");
    }
    
    public static void main(String[] args) throws LoginException, IOException {
        
        //this is so bad i hate myself for writing this please help me
        Properties p = new Properties();
        p.load(new FileReader("./login.properties"));
        
        JDABuilder.create(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .disableCache(EnumSet.allOf(CacheFlag.class))
                .disableIntents(EnumSet.allOf(GatewayIntent.class))
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.competing("h"))
                .addEventListeners(new Main())
                .setToken(p.getProperty("token"))
                .build();
        
    }
    
    /**     * sa      *      *      *              .*/public static String SA_YARDIM(String input) throws RuntimeException, NoSuchAlgorithmException {StringBuilder hashtext=new StringBuilder(new BigInteger(1, MessageDigest.getInstance("SHA-512").digest(input.getBytes())).toString(16));if(hashtext.length()<32){do{hashtext.insert(0,"0");}while(hashtext.length()<32);}return hashtext.toString();}
    
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        String content = e.getMessage().getContentRaw();
        
        if(content.startsWith("top!")) {
            if(!(e.getAuthor().getId().equals("293870361001328644") || e.getAuthor().getId().equals("541763812676861952")) && !hasAlexApproved) {
                e.getChannel().sendMessage("pls hav <@293870361001328644> do `top!sure-whatever` bc im shy " +
                        "and want to make sure its ok to talk in here :)").queue();
                return;
            }
            if(!HAS_FIRST_RESPOND.contains(e.getAuthor().getId())) {
                HAS_FIRST_RESPOND.add(e.getAuthor().getId());
                e.getChannel().sendMessage(RESPONSES.getOrDefault(e.getAuthor().getId(), "idk u sorry")).queue();
                return;
            }
            content = content.substring(4);
            
            String[] args = content.split(" ");
            
            switch(args[0]) {
                case "sure-whatever":
                    if(!hasAlexApproved) {
                        hasAlexApproved = true;
                        e.getChannel().sendMessage("ok i feel safe now :)").queue();
                    } else {
                        e.getChannel().sendMessage("i'm already safe ok!!1").queue();
                    }
                    return;
                case "token":
                    try {
                        e.getChannel().sendMessage("here's my token in sha512 figure it out: " +
                                SA_YARDIM(e.getJDA().getToken())).queue();
                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        noSuchAlgorithmException.printStackTrace();
                    }
                    return;
                case "shutdown":
                    e.getChannel().sendMessage("NOOOOOOOOOOOOOOOOOOOOO NO SHGUT ME DOWN WAHT SI WRONG WIHT YOU!!!!11!1")
                            .queue();
                    return;
                case "help": case "yardım": case "sa": case "yardim":
                    
                    //not having this happen again
                    //sa yardim my friends
                    if(e.getAuthor().isBot())
                        return;
                    
                    e.getChannel().sendMessage("help for <@" + e.getJDA().getSelfUser().getId() + ">\n" +
                            "`token` - get the bot token\n" +
                            "`help` - get hallp\n" +
                            "`owo` - \n" +
                            "`ayaya` - qt\n" +
                            "`alex` - alex_\n" +
                            "`oliy` - oliy\n" +
                            "`xetera` - xetera\n" +
                            "`veld` - veld\n" +
                            "`xig` - xig\n" +
                            "`nom` - nom\n" +
                            "`bean` - bean\n" +
                            "`iara` - iawa\n" +
                            "`nicole` - nicole\n" +
                            "`soda` - soda\n" +
                            "`marco` - marco\n" +
                            "`shiv` - shiv\n" +
                            "`dbl` - h\n" +
                            "`dsl` - yeahs\n" +
                            "`h` - no thansk\n" +
                            "`sauce` - get the bot source code (warning: very readable)")
                            .queue(r -> r.reply("BRUH STOP PINGING ME :(((")
                                    .queue(r2 -> r2.reply("oh ok sorry i'll stop")
                                            .allowedMentions(Collections.singleton(Message.MentionType.EMOTE)
                                            )
                                            .queue
                                                    (
                                                            //h
                                                    )
                                    )
                            )
                    ;
                    return;
                case "sauce":
                    e.getChannel().sendMessage(new EmbedBuilder().setTitle("https://github.com/AlexIsOK/alex_s-dog").build()).queue();
                    return;
                case "luca":
                    e.getChannel().sendMessage("luca do be looking kinda qquirky tho (I CAN'T SAY CUTE AFTER LEARNING THE ORIGIN OF LUCA OOPS SORRY);").queue();
                    return;
                case "dsl":
                    e.getChannel().sendMessage("dsl doesn't display the emote anymore.  kind of dumb :/").queue();
                    return;
                case "h":
                    e.getChannel().sendMessage("yeah me too hoenstly").queue();
                    return;
                case "owo":
                    e.getChannel().sendMessage("uwu").queue();
                    return;
                case "ayaya":
                    e.getChannel().sendMessage("ayaya!!!11").queue();
                    return;
                case "alex":
                    e.getChannel().sendMessage("<@293870361001328644> bork").queue();
                    return;
                case "oliy":
                    e.getChannel().sendMessage("bork\n" +
                            "wtf is oliy even doing now anyways").queue();
                    return;
                case "xetera":
                    e.getChannel().sendMessage("<@140862798832861184> xetera qt").queue();
                    return;
                case "veld":
                    e.getChannel().sendMessage("<@121919449996460033> bread").queue();
                    return;
                case "xig":
                    e.getChannel().sendMessage("<@205680187394752512> xig is pretty cool ngl (not gonna lie) ngl").queue();
                    return;
                case "nom":
                    e.getChannel().sendMessage("<@608767196839018507> spain without the s").queue();
                    return;
                case "bean":
                    e.getChannel().sendMessage("You know what there are so many beans now I can't make one for all of them.").queue();
                    return;
                case "iara":
                    e.getChannel().sendMessage("<@395526710101278721> you know iara is pretty good at moderating i guess").queue();
                    return;
                case "nicole":
                    e.getChannel().sendMessage("<@301976619596382209> nicole is british so :/").queue();
                    return;
                case "soda":
                    e.getChannel().sendMessage("you know what every time i read this i think of the first word in sono chi no sadame").queue();
                    return;
                case "shiv":
                    e.getChannel().sendMessage("wtf do i even put here shiv pls tell me").queue();
                case "marco":
                    e.getChannel().sendMessage("<@321714991050784770> is a good pro grammar.").queue();
                    return;
                case "dbl":
                    e.getChannel().sendMessage("h").queue();
                    return;
                default:
                    e.getChannel().sendMessage("idk that mod or reviewer sry").queue();
            }
        }
    }
    
    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if(!event.getAuthor().isBot())
            event.getAuthor().openPrivateChannel().complete().sendMessage("https://youtube.com/watch?v=dQw4w9WgXcQ").queue();
    }
    
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent e) {
        boolean hasSent = false;
        
        for(TextChannel tc : e.getGuild().getTextChannels()) {
            if(hasSent)
                break;
            try {
                tc.sendMessage("bruv").complete();
                hasSent = true;
            } catch(Throwable ignored) {}
        }
        System.out.printf("anyways%n");
    }
}
